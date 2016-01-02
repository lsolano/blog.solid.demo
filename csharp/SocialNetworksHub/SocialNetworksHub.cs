using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Malpeza.Solid.Demo.OCP
{
    
    public interface SocialNetworksHub
    {

        void AddCollector(SocialNetworkPullCollector twCollector);

        IEnumerable<Entry> GetEntriesSince(DateTime since);
    }
}
