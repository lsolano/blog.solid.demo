using Malpeza.Solid.Demo.OCP.Collectors;
using Malpeza.Solid.Demo.OCP.Filters;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Malpeza.Solid.Demo.OCP
{
    public class CleanSocialNetworksHub : SocialNetworksHub
    {
        private readonly IList<SocialNetworkPullCollector> collectors;
        private readonly IList<EntriesFilter> filters;

        public CleanSocialNetworksHub()
        {
            this.collectors = new List<SocialNetworkPullCollector>();
            this.filters = new List<EntriesFilter>();
        }

        public void AddCollector(SocialNetworkPullCollector collector)
        {
            this.collectors.Add(collector);
        }

        public void AddFilter(EntriesFilter filter)
        {
            this.filters.Add(filter);
        }

        public IEnumerable<Entry> GetEntriesSince(DateTime since)
        {
            var entries = this.collectors.SelectMany(collector => collector.Collect(since));

            return FilterEntries(entries).OrderBy(entry => entry.Date).Reverse();
        }

        private IEnumerable<Entry> FilterEntries(IEnumerable<Entry> entries)
        {
            var filterOutHashes = new ConcurrentBag<String>();

            Parallel.ForEach(this.filters, (filter) =>
            {
                IEnumerable<String> invalidEntriesHashes = filter.Filter(entries);
                foreach (var hash in invalidEntriesHashes)
                {
                    filterOutHashes.Add(hash);
                }
            });

            var finalExclusions = filterOutHashes.Distinct().ToArray();

            return entries.Where(entry => !finalExclusions.Contains(entry.Hash));
        }
    }
}
