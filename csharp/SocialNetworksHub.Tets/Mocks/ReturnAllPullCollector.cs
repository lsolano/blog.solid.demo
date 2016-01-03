using Malpeza.Solid.Demo.OCP.Collectors;
using System;
using System.Collections.Generic;

namespace Malpeza.Solid.Demo.OCP.Tests
{
    class ReturnAllPullCollector : SocialNetworkPullCollector
    {
        private IEnumerable<Entry> entries;

        public ReturnAllPullCollector(IEnumerable<Entry> entries)
        {
            this.entries = entries;
        }

        public IEnumerable<Entry> Collect(DateTime since)
        {
            return this.entries;
        }
    }
}
