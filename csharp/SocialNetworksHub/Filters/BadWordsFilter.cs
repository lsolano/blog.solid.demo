using System;
using System.Collections.Generic;
using System.Linq;

namespace Malpeza.Solid.Demo.OCP.Filters
{
    public sealed class BadWordsFilter : EntriesFilter
    {
        private readonly ISet<String> badWords = new HashSet<String>();

        public BadWordsFilter(IEnumerable<String> badWords)
        {
            var words = badWords ?? new List<String>();
            var normalizedWords = words.Where(word => !String.IsNullOrWhiteSpace(word))
                                            .Select(word => word.ToLowerInvariant().Trim());
            foreach (var word in normalizedWords)
            {
                this.badWords.Add(word);
            }
        }

        public IEnumerable<string> Filter(IEnumerable<Entry> entries)
        {
            foreach (var entry in entries.Where(entry => IsBadEntry(entry)))
            {
                yield return entry.Hash;
            }

            yield break;
        }

        private bool IsBadEntry(Entry entry)
        {
            if (this.badWords.Any(badWord => entry.Text.ToLowerInvariant().Contains(badWord)))
            {
                return true;
            }

            var lowerTags = entry.Tags.Select(tag => tag.ToLowerInvariant());
            if (lowerTags.Any(tag => this.badWords.Any(badWord => tag.Contains(badWord))))
            {
                return true;
            }

            return false;
        }
    }
}
