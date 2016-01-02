using System;
using System.Collections.Generic;

namespace Malpeza.Solid.Demo.OCP
{
    public class Entry
    {
        private readonly string text;
        private readonly Contact author;
        private readonly DateTime date;
        private readonly IEnumerable<String> tags;
        private readonly SocialNetwork source;

        public Entry(string text, Contact author, DateTime date, IEnumerable<String> tags, SocialNetwork source)
        {
            this.text = text;
            this.author = author;
            this.date = date;
            this.tags = tags ?? new List<String>();
            this.source = source;
        }

        public string Text { get { return this.text; } }
        public Contact Author { get { return this.author; } }
        public DateTime Date { get { return this.date; } }
        public IEnumerable<String> Tags { get { return this.tags; } }
        public SocialNetwork Source { get { return this.source; } }
    }
}
