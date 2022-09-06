class Tree {
    int age;
    double height;
 
    public Tree() {
        age = 0;
        height = 0.0;
    }
 
    void growTaller() {
        height = height + 0.3;
    }
    
    void growOlder() {
        age = age + 1;
    }
 }
