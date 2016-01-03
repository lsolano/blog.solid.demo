using System;
using System.Collections.Generic;

namespace Malpeza.Solid.Demo.OCP.Collectors
{
    public class TwitterPullCollector : SocialNetworkPullCollector
    {
        private readonly string restAPIBaseUrl;

        public TwitterPullCollector(string restAPIBaseUrl)
        {
            this.restAPIBaseUrl = restAPIBaseUrl;
        }

        public IEnumerable<Entry> Collect(DateTime since)
        {
            throw new NotImplementedException();
        }
    }
}
