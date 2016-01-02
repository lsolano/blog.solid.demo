using System;
using System.Collections.Generic;
using System.Linq;

namespace Malpeza.Solid.Demo.OCP
{
    public class DirtySocialNetworksHub : SocialNetworksHub
    {
        private readonly IList<SocialNetworkPullCollector> collectors;
        private readonly bool removeEntriesWithBadWords;

        private readonly IEnumerable<String> badWords 
                = (new List<String> { "nestedLoops", "deadCode", "10levelNestedLoops" })
                    .Select(word => word.ToLowerInvariant()).ToList();

        public DirtySocialNetworksHub()
            : this(false)
        {
        }

        public DirtySocialNetworksHub(bool removeEntriesWithBadWords)
        {
            this.collectors = new List<SocialNetworkPullCollector>();
            this.removeEntriesWithBadWords = removeEntriesWithBadWords;
        }

        public void AddCollector(SocialNetworkPullCollector collector)
        {
            this.collectors.Add(collector);
        }

        public IEnumerable<Entry> GetEntriesSince(DateTime since)
        {
            var entries = this.collectors.SelectMany(collector => collector.Collect(since));

            if (this.removeEntriesWithBadWords) {
                return entries.Where(entry => 
                    !this.badWords.Any(badWord => entry.Text.ToLowerInvariant().Contains(badWord))
                 && (!entry.Tags.Any() || entry.Tags.Select(tag => tag.ToLowerInvariant())
                        .Where(tag => this.badWords.Any(badWord => tag.Contains(badWord))).Count() == 0))
                                .OrderBy(entry => entry.Date).Reverse();
            }

            return entries.OrderBy(entry => entry.Date).Reverse();
        }
    }
}
