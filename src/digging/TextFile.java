package digging;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class TextFile implements List<String> {

    public String FilePath;
    private Scanner sin;
    private PrintWriter fout;
    private List<String> data;
    //Constructors

    TextFile() {
    }

    TextFile(String path) throws IOException {
        this.FilePath = path;
        Initialize(path);
    }
    
    public void Initialize(String path) throws IOException{
        //Try to ressurect the previous data
        try{
            Read();
            Write(data);
        }catch(FileNotFoundException e){
            System.out.println("creating new file");
            fout = new PrintWriter(new FileWriter(path));
            fout.write("");
        }
        //this.FilePath = path;
        //fout = new PrintWriter(new FileWriter(HardAccess(path)));
        //System.out.println("creating new file");
        //fout.write("");
    }
    
    public void Destroy(){
        System.err.println("TODO: Destroy");
    }
    
    /*public void (){
        System.err.println("TODO: Destroy");
    }*/

    private List<String> Read() throws FileNotFoundException {
        List<String> data = new ArrayList<String>();
        sin = new Scanner(new FileReader(FilePath));

        while (sin.hasNext()) {
            data.add(sin.nextLine());
        }
        sin.close();
        this.data = data;
        return data;
    }

    private void Write(List<String> data) throws IOException {
        Iterator<String> dataIterator = data.iterator();
        fout = new PrintWriter(new FileWriter(FilePath));
        fout.write("");
        while (dataIterator.hasNext()) {
            fout.println(dataIterator.next());
        }
        fout.close();
    }

    private void Error() {
        System.err.println("Error within TextFile class.");
    }

    @Override
    public int size() {
        try {
            return Read().size();
        } catch (FileNotFoundException ex) {
            Error();
            return 0;
        }
    }

    @Override
    public boolean isEmpty() {
        try {
            return Read().isEmpty();
        } catch (FileNotFoundException ex) {
            Error();
            return false;
        }
    }

    @Override
    public boolean contains(Object o) {
        try {
            return Read().contains(o);
        } catch (FileNotFoundException ex) {
            Error();
            return false;
        }
    }

    @Override
    public Iterator<String> iterator() {
        try {
            return Read().iterator();
        } catch (FileNotFoundException ex) {
            Error();
            return null;
        }
    }

    @Override
    public Object[] toArray() {
        try {
            return Read().toArray();
        } catch (FileNotFoundException ex) {
            Error();
            return null;
        }
    }

    @Override
    public <T> T[] toArray(T[] a) {
        try {
            return Read().toArray(a);
        } catch (FileNotFoundException ex) {
            Error();
            return null;
        }
    }

    @Override
    public boolean add(String e) {
        try {
            Read();
            data.add(e);
            Write(data);
            return true;
        } catch (IOException ex) {
            Error();
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        try {
            Read();
            data.remove(o);
            Write(data);
            return true;
        } catch (IOException ex) {
            Error();
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        try {
            return Read().containsAll(c);
        } catch (FileNotFoundException ex) {
            Error();
            return false;
        }
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        try {
            Read();
            data.addAll(c);
            Write(data);
            return true;
        } catch (IOException ex) {
            Error();
            return false;
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        try {
            Read();
            data.addAll(index, c);
            Write(data);
            return true;
        } catch (IOException ex) {
            Error();
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        try {
            Read();
            data.removeAll(c);
            Write(data);
            return true;
        } catch (IOException ex) {
            Error();
            return false;
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        try {
            Read();
            data.removeAll(c);
            Write(data);
            return true;
        } catch (IOException ex) {
            Error();
            return false;
        }
    }

    @Override
    public void clear() {
        try {
            Read();
            data.clear();
            Write(data);
        } catch (IOException ex) {
            Error();
        }
    }

    @Override
    public String get(int index) {
        try {
            return Read().get(index);
        } catch (FileNotFoundException ex) {
            Error();
            return null;
        }
    }

    @Override
    public String set(int index, String element) {
        try {
            Read();
            String s = data.set(index, element);
            Write(data);
            return s;

        } catch (IOException ex) {
            Error();
            return null;
        }
    }

    @Override
    public void add(int index, String element) {
        try {
            Read();
            data.add(index, element);
            Write(data);
        } catch (IOException ex) {
            Error();
        }
    }

    @Override
    public String remove(int index) {
        try {
            Read();
            String s = data.remove(index);
            Write(data);
            return s;
        } catch (IOException ex) {
            Error();
            return null;
        }
    }

    @Override
    public int indexOf(Object o) {
        try {
            return Read().indexOf(o);
        } catch (FileNotFoundException ex) {
            Error();
            return 0;
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        try {
            return Read().lastIndexOf(o);
        } catch (FileNotFoundException ex) {
            Error();
            return 0;
        }
    }

    @Override
    public ListIterator<String> listIterator() {
        try {
            return Read().listIterator();
        } catch (FileNotFoundException ex) {
            Error();
            return null;
        }
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        try {
            return Read().listIterator(index);
        } catch (FileNotFoundException ex) {
            Error();
            return null;
        }
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        try {
            return Read().subList(fromIndex, toIndex);
        } catch (FileNotFoundException ex) {
            Error();
            return null;
        }
    }
}