using System.Collections.Generic;

namespace Malpeza.Solid.Demo.OCP.Filters
{
    public interface EntriesFilter
    {
        /// <summary>
        /// Returns bad entries hashes.
        /// </summary>
        /// <param name="entries">Actual entries to filter</param>
        /// <returns>Returns bad entries hashes, or empty enumeration if all are good.</returns>
        IEnumerable<string> Filter(IEnumerable<Entry> entries);
    }
}
