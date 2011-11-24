package ru.arbuzz.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
@Root(name = "roster")
public class Roster {

    @ElementList(inline = true, required = false)
    private List<RosterElement> contacts;

    public Roster() {}

    public List<RosterElement> getContacts() {
        return contacts;
    }

    public void setContacts(List<RosterElement> contacts) {
        this.contacts = contacts;
    }

    public void sortElements() {
        Collections.sort(contacts, new RosterElementComparator());
    }

    public static class RosterElementComparator implements Comparator<RosterElement> {

        public RosterElementComparator() {}

        @Override
        public int compare(RosterElement rosterElement, RosterElement rosterElement1) {
            if (rosterElement.isOnline() && rosterElement1.isOnline()) {
                return 0;
            } else if (rosterElement.isOnline()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
