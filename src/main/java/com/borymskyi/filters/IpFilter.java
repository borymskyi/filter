package com.borymskyi.filters;

public abstract class IpFilter {

    private IpFilter nextFilter;

    public static IpFilter link(IpFilter firstFilter, IpFilter... filterInChain) {
        IpFilter head = firstFilter;
        for (IpFilter nextFilterInChain: filterInChain) {
            head.nextFilter = nextFilterInChain;
            head = nextFilterInChain;
        }
        return firstFilter;
    }

    //Returns true if the passed IP passes a filter check.
    public abstract boolean filter(String ip);

    protected boolean nextFiltering(String ip) {
        if (hasNext()) {
            return nextFilter.filter(ip);
        } else {
            return true;
        }
    }

    private boolean hasNext() {
        return nextFilter != null;
    }
}
