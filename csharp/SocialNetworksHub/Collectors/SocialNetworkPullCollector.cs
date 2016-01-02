using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Malpeza.Solid.Demo.OCP
{
    public interface SocialNetworkPullCollector
    {
        IEnumerable<Entry> Collect(DateTime since);
    }
}
