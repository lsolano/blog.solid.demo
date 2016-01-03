using Malpeza.Solid.Demo.OCP.Collectors;
using System;
using System.Collections.Generic;

namespace Malpeza.Solid.Demo.OCP.Tests
{
    public class EmptySourcePullCollector: SocialNetworkPullCollector
    {
        public IEnumerable<Entry> Collect(DateTime since)
        {
            return new List<Entry>();
        }
    }
}
