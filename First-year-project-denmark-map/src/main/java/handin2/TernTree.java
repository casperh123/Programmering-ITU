package handin2;

import Exceptions.NoSuchAddressException;
import MapObjects.Markers.Address;

import java.util.*;

public class TernTree {

    // When Ternary Search Tree is instantiated
    TernNode root;
    //Contains accepted characters. If not contained, characters are ignored.
    Set<Character> alphabet = new HashSet<>(Arrays.asList(
            '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','é','f','g','h','i','j','k',
            'l','m','n','o','p', 'q','r','s','t','u','v','w','x','y','ü','z','æ','ø','ö','å','ä'));

    List<Address> matches;
    short matchCounter;

    public TernTree() {
        root = null;
        matches = new ArrayList<>();
        matchCounter = 0;
    }

    //Don't use this method. It's only for internal use.
    private TernNode insertInternal(TernNode node, Address address, String addrString) {
        // If string is empty, the returned node is not recognized, and an alert is showed in gui
        if (addrString.length() < 1) {
            return node;
        }
        char head = addrString.charAt(0);
        char[] data;
        String dataString;
        if (addrString.length() > 1) {
            data = addrString.substring(1).toCharArray();
            dataString = new String(data);
        } else {
            data = new char[0];
            dataString = "";
        }

        if (alphabet.contains(head)) {
            if (node == null) {
                node = new TernNode(head);
            }

            if (head < node.data) {
                node.left = insertInternal(node.left, address, addrString);
            } else if (head > node.data) {
                node.right = insertInternal(node.right, address, addrString);
            } else {
                if (data.length == 0) {
                    node.isEnd = address;
                } else {
                    node.middle = insertInternal(node.middle, address, dataString);
                }
            }
        } else {
            node = insertInternal(node, address, dataString);
        }

        return node;
    }

    //Insert String into tree
    public void insert(Address address) {
        String addrString = address.toString().toLowerCase();
        root = insertInternal(root, address, addrString);
    }

    //Insert List of Strings into tree
    public void insert(List<Address> addrList) {
        for (Address address : addrList) {
            String addrString = address.toString().toLowerCase();
            root = insertInternal(root, address, addrString);
        }
    }

    // Finds last node in prefix
    public TernNode search(TernNode node, String prefix) {

        if (node == null || prefix.length() == 0) {
            throw new NoSuchElementException("Tree or input is empty");
        }

        //The first character in prefix: used for comparison with current node
        char head = prefix.charAt(0);
        //Rest of prefix
        char[] data;
        String dataString;

        //We can't split prefix, if it's no more than 1 char long
        if (prefix.length() > 1) {
            data = prefix.substring(1).toCharArray();
            dataString = new String(data);
        } else {
            data = new char[0];
            dataString = "";
        }

        if (alphabet.contains(head)) {

            if (head < node.data) {
                return search(node.left, prefix);
            } else if (head > node.data) {
                return search(node.right, prefix);
            } else {
                if (data.length == 0) {
                    return node;
                }
                return search(node.middle, dataString);
            }
        } else {
            // Search is called on the same node, if there are still chars left in data
            // else, just return node
            // Can still find matches even though last char is not in alphabet
            if (data.length > 0) {
                node = search(node, dataString);
            } else {
                return node;
            }
        }
        return node;
    }

    // Searches the prefix, and finds all valid endings/suffixes for it
    private void findSuffixes(TernNode node, String prefix) {
        StringBuilder stringBuilder = new StringBuilder(prefix);
        //Guard clause for null nodes
        if (node == null) {
            return;
        } //Guard clause for when we have the desired amount of matches already
        if (matchCounter >= 5) {
            return;
        } //Actually adding strings to list
        if (node.isEnd != null) {
            matchCounter++;
            matches.add(node.isEnd);
        }

        findSuffixes(node.left, prefix);

        findSuffixes(node.middle, String.valueOf(stringBuilder.append(node.data)));
        stringBuilder.deleteCharAt(prefix.length() - 1);

        findSuffixes(node.right, prefix);
    }

    // Uses private findSuffixes to find matches
    public void findSuffixes(String prefix) {
        matches.clear();
        matchCounter = 0;
        //Using search().middle, because we need the final searched node to be included
        TernNode node = search(root, prefix.toLowerCase()).middle;
        if (prefix.length() < 2) {
            findSuffixes(node, prefix);
        } else {
            String updatedPrefix = prefix.substring(0, prefix.length() - 1);
            findSuffixes(node, updatedPrefix);
        }
    }

    // Returns the address object that corresponds the prefix.
    // The prefix will be a string of an entire address when this method is used.
    public Address getFinalAddress(String prefix) throws NoSuchAddressException {

        TernNode addressNode;

        if(prefix == null) {
            throw new NoSuchAddressException("");
        }

        try {
            addressNode = search(root, prefix.toLowerCase());
        } catch (NoSuchElementException e) {
            throw new NoSuchAddressException(prefix);
        }
        return addressNode.isEnd;
    }

    public List<Address> getMatches() {
        return matches;
    }

    public void clearMatches() {
        matches.clear();
    }

    // Node inner class
    protected static class TernNode {
        char data;
        TernNode left, middle, right;
        Address isEnd;

        // Node constructor
        protected TernNode(char data) {
            this.data = data;
            this.left = null;
            this.middle = null;
            this.right = null;
            this.isEnd = null;
        }
    }

}
