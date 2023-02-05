package bfst2023.handins;

import java.util.regex.Pattern;

public class Address {
        public final String street, house, floor, side, postcode, city;
    
      private Address(
          String _street, String _house, String _floor, String _side, 
          String _postcode, String _city) {
        street = _street;
        house = _house;
        floor = _floor;
        side = _side;
        postcode = _postcode;
        city = _city;
      }
    
      public String toString() {
        return street + " " + house + ", " + floor + " " + side + "\n"
          + postcode + " " + city;
      }
      
      private final static String REGEX = "^(?<street>[A-ZÆØÅa-zæåø ,]+)\\s+(?<house>[0-9]+[A-Za-z]?)[ 0,.]\\s?(?<floor>[1-9|STst]+)?[ .,]*(?<side>[A-Za-z]+)?\\s+(?<postcode>[0-9 ]{4})\\s+(?<city>[A-ZØÆÅa-zæøå, ]+)[.]?$";
      private final static Pattern PATTERN = Pattern.compile(REGEX);
    
      public static Address parse(String input) {
            var builder = new Builder();
            var matcher = PATTERN.matcher(input);
            if (matcher.matches()) {
                builder.postcode(matcher.group("postcode"));
                builder.city(matcher.group("city"));
                builder.house(matcher.group("house"));
                builder.street(matcher.group("street"));
                builder.floor(matcher.group("floor"));
                builder.side(matcher.group("side"));
            }
            return builder.build();
      }
    
    
      public static class Builder {
        private String street, house, floor, side, postcode, city;
    
        public Builder street(String _street) {
          street = _street;
          return this;
        }
    
        public Builder house(String _house) {
          house = _house;
          return this;
        }
    
        public Builder floor(String _floor) {
          floor = _floor;  
          return this;
        }
    
        public Builder side(String _side) {
          side = _side;
          return this;
        }
    
        public Builder postcode(String _postcode) {
          postcode = _postcode;
          return this;
        }
    
        public Builder city(String _city) {
          city = _city;
          return this;
        }
        public Address build() {
          return new Address(street, house, floor, side, postcode, city);
        }  
        
      }
    
      

}
