package reflection.class_object.package_private.internal;

import reflection.class_object.package_private.Interface;

public class InternalClass implements Interface {
    private final InternalClass2 internalClass2;

    public InternalClass(InternalClass2 internalClass2) {
        this.internalClass2 = internalClass2;
    }

    @Override
    public String getName() {
        return this.internalClass2.getTestString();
    }
}
