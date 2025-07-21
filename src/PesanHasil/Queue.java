package PesanHasil;
public class Queue<E> extends LinkedList<E>{
    public Queue(){

    }
    public Object peek(){
        return getFirst();
    }

    //menambahkan antrian baru
    public void enqueue(E e){
        addLast(e);
    }
    //mengambil dan menghapus antrian
    public Object dequeue(){
        return removeFirst();
    }
    //menampilkan antrian
    public void display(){
        for(int x = 0 ; x < this.size() ; x++){
            System.out.println(this.get(x));
        }
    }
    public java.util.List<E> toList() {
    java.util.List<E> result = new java.util.ArrayList<>();
    for (int i = 0; i < this.size(); i++) {
        result.add((E) this.get(i));
    }
    return result;
}
    

    // public Object cancel(int nomorAntrian){
    //     for(int x = 0 ; x < this.size();x++){
    //         Pelanggan p = (Pelanggan) this.get(x);
    //         if(p.getNomorAntrian() == nomorAntrian){
    //             return remove(x);
    //         }
    //     }
    //     return null;
    // }
}