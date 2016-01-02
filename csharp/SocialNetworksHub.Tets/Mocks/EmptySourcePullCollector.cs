using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

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
