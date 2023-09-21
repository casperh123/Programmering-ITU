package Interfaces;

import MapObjects.Markers.Address;
import handin2.Settings.PathfindingSettings;

import java.util.Collection;

public interface iTraversable extends iDrawable {

    double getLength();
    int getSpeedLimit();
    int getID();
    Collection<Address> getAddresses();
    String getType();
    String getName();
    boolean isOneWay();

    default boolean isDriveable() {
        return PathfindingSettings.isDriveable(getType());
    }

    default boolean isCycleable() {
        return PathfindingSettings.isCycleable(getType());
    }

    default boolean isWalkable() {
        return PathfindingSettings.isWalkable(getType());
    }

}