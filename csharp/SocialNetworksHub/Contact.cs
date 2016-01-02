using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Malpeza.Solid.Demo.OCP
{
    public class Contact
    {
        private readonly string name;

        public Contact(string name)
        {
            this.name = name;
        }

        public override bool Equals(object obj)
        {
            if (obj is Contact)
            {
                return this.name.Equals((obj as Contact).name, StringComparison.InvariantCulture);
            }

            return false;
        }

        public override int GetHashCode()
        {
            return (this.name ?? String.Empty).PadRight(1).Substring(0, 1).GetHashCode();
        }
    }
}
