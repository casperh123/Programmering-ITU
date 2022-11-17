package test;
import src.CustomerTracker;
import src.MockDB;
import src.GUIView;

public class test {

        
        MockDB db = new MockDB();

        CustomerTracker customerTracker = new CustomerTracker(db, 47);

        GUIView guiView = new GUIView(db, customerTracker);

    
}
