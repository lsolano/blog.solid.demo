using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Malpeza.Solid.Demo.OCP
{
    public class SocialNetwork
    {
        private readonly string name;

        public SocialNetwork(string name)
        {
            this.name = name;
        }

        public override bool Equals(object obj)
        {
            if (obj is SocialNetwork)
            {
                return this.name.Equals((obj as SocialNetwork).name, StringComparison.InvariantCulture);
            }

            return false;
        }

        public override int GetHashCode()
        {
            return (this.name ?? String.Empty).PadRight(1).Substring(0, 1).GetHashCode();
        }
    }
}
