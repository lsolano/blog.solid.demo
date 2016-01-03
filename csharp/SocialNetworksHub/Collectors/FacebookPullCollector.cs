using System;
using System.Collections.Generic;

namespace Malpeza.Solid.Demo.OCP.Collectors
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
