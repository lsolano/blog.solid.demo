using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Linq;

namespace Malpeza.Solid.Demo.OCP.Tests
{
    [TestFixture]
    public class DirtySocialNetworksHubTests
    {
        [Test]
        public void Test_Empty_Sources()
        {
            SocialNetworksHub hub = new DirtySocialNetworksHub();

            SocialNetworkPullCollector twCollector = new EmptySourcePullCollector();
            SocialNetworkPullCollector fbCollector = new EmptySourcePullCollector();
            hub.AddCollector(twCollector);
            hub.AddCollector(fbCollector);

            DateTime since = DateTime.Now.AddHours(-1);
            IEnumerable<Entry> lastHourEntries = hub.GetEntriesSince(since);

            Assert.False(lastHourEntries.Any());
        }

        [Test]
        public void Test_Entries_Are_In_Date_Order()
        {
            SocialNetworksHub hub = new DirtySocialNetworksHub();
            var peter = new Contact("Peter");
            var jhon = new Contact("John");
            var mary = new Contact("Mary");

            var fb = new SocialNetwork("facebook");
            var tw = new SocialNetwork("twitter");
            var ins = new SocialNetwork("instagram");
            var tags = new List<string>();

            IEnumerable<Entry> entries = new List<Entry>() {
                new Entry("second", peter, DateTime.Now.AddHours(-2), tags, fb),
                new Entry("third", jhon, DateTime.Now.AddHours(-3), tags, tw),
                new Entry("first", mary, DateTime.Now.AddHours(-1), tags, ins)
            };

            SocialNetworkPullCollector returnAllCollector = new ReturnAllPullCollector(entries);
            hub.AddCollector(returnAllCollector);

            DateTime since = DateTime.Now.AddHours(-4);
            IEnumerable<Entry> hubEntries = hub.GetEntriesSince(since);

            var first = hubEntries.Take(1).First();
            var second = hubEntries.Take(2).Last();
            var third = hubEntries.Last();

            Assert.True("first".Equals(first.Text, StringComparison.InvariantCulture));
            Assert.True("second".Equals(second.Text, StringComparison.InvariantCulture));
            Assert.True("third".Equals(third.Text, StringComparison.InvariantCulture));
        }

        [Test]
        public void Test_Filter_Entries_With_Bad_Words()
        {
            SocialNetworksHub hub = new DirtySocialNetworksHub(true);
            var peter = new Contact("Peter");
            var jhon = new Contact("John");
            var mary = new Contact("Mary");

            var fb = new SocialNetwork("facebook");
            var tw = new SocialNetwork("twitter");
            var ins = new SocialNetwork("instagram");
            var badTags = new List<string>() { 
                "#deadCode", "#complexCode", "#10levelNestedLoops"
            };

            var goodTags = new List<string>() { 
                "#shortCode", "#simpleCode", "#2nestedLevelsMax"
            };

            IEnumerable<Entry> entries = new List<Entry>() {
                new Entry("second dead code", peter, DateTime.Now.AddHours(-2), badTags, fb),
                new Entry("third 2000LOC functions", jhon, DateTime.Now.AddHours(-3), badTags, tw),
                new Entry("first Clean Code! Really?", mary, DateTime.Now.AddHours(-1), badTags, ins),
                new Entry("first Clean Code!!!", mary, DateTime.Now.AddHours(-1), goodTags, ins)
            };

            SocialNetworkPullCollector returnAllCollector = new ReturnAllPullCollector(entries);
            hub.AddCollector(returnAllCollector);

            DateTime since = DateTime.Now.AddHours(-4);
            IEnumerable<Entry> hubEntries = hub.GetEntriesSince(since);

            Assert.AreEqual(1, hubEntries.Count());
            var cleanEntry = hubEntries.First();

            Assert.AreEqual("first Clean Code!!!", cleanEntry.Text);
        }
    }
}
