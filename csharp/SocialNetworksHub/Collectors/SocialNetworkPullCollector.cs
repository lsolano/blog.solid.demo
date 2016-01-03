using System;
using System.Collections.Generic;

namespace Malpeza.Solid.Demo.OCP.Collectors
{
    public interface SocialNetworkPullCollector
    {
        IEnumerable<Entry> Collect(DateTime since);
    }
}
