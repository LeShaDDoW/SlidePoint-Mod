package net.lstwo.slidepoint.object;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Slide {
    private String name;
    private List<SlideObject> components = new List<SlideObject>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<SlideObject> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(SlideObject slideObject) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends SlideObject> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends SlideObject> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public SlideObject get(int index) {
            return null;
        }

        @Override
        public SlideObject set(int index, SlideObject element) {
            return null;
        }

        @Override
        public void add(int index, SlideObject element) {

        }

        @Override
        public SlideObject remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<SlideObject> listIterator() {
            return null;
        }

        @Override
        public ListIterator<SlideObject> listIterator(int index) {
            return null;
        }

        @Override
        public List<SlideObject> subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    public Slide(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
