package org.wuzl.test.concurrent.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class TestAtomicReferenceFieldUpdater {
    public static void main(String[] args) {
        // AtomicReferenceFieldUpdater<Person, Long> update = AtomicReferenceFieldUpdater.newUpdater(Person.class,
        // Long.class, "id");// 字段不能是private
        // Person person = new Person();
        // update.set(person, 1l);
        // System.out.println(person.getId());
        AtomicReferenceFieldUpdater<Person2, Long> update = AtomicReferenceFieldUpdater.newUpdater(Person2.class,
                Long.class, "id");// 字段不能是private
        Person2 person = new Person2();
        update.set(person, 1l);
        System.out.println(person.getId());
    }

    static class Person2 {
        volatile Long id;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }
}
