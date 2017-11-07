package com.company;

import java.util.ArrayList;
import java.util.List;

//Base abstract class for parsers
public abstract class AbstractPlanesBuilder {
    protected List<Plane> planes;
    public AbstractPlanesBuilder(){
        planes = new ArrayList<>();
    }
    public List<Plane> getPlanes(){
        return planes;
    }
    abstract public void buildListPlanes(String fileName);
}
