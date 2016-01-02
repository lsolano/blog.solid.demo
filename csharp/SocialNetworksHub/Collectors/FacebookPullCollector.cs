using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Malpeza.Solid.Demo.OCP
{
    public class FacebookPullCollector : SocialNetworkPullCollector
    {
        private readonly string restAPIBaseUrl;

        public FacebookPullCollector(string restAPIBaseUrl)
        {
            this.restAPIBaseUrl = restAPIBaseUrl;
        }

        public IEnumerable<Entry> Collect(DateTime since)
        {
            throw new NotImplementedException();
        }
    }
}
