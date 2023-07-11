package org.example;

import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;

import java.util.ArrayList;
import java.util.List;

public class CustomAgendaFilter implements AgendaFilter
{
    List<String> filters = new ArrayList<String>();

    public void addFilter(String filter)
    {
        filters.add(filter);
    }

    @Override
    public boolean accept(Match match) {

        for(String filter: filters)
        {
            if(match.getRule().getName().contains(filter))
            {
                return true;
            }
        }

        return false;
    }
}
