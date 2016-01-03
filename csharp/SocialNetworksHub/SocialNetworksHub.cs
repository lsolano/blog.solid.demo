using Malpeza.Solid.Demo.OCP.Collectors;
using Malpeza.Solid.Demo.OCP.Filters;
using System;
using System.Collections.Generic;

namespace Malpeza.Solid.Demo.OCP
{
    
    public interface SocialNetworksHub
    {

        void AddCollector(SocialNetworkPullCollector twCollector);

        void AddFilter(EntriesFilter filter);

        IEnumerable<Entry> GetEntriesSince(DateTime since);
    }
}
