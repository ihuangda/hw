// Ida Huang
// Period 8
// HW38
// 2013-12-04

/*==================================================
  class SuperArray version 2.0
  Wrapper class for array. Facilitates:
  resizing 
  expansion 
  read/write capability on elements
  adding an element to end of array
  adding an element at specified index
  removing an element at specified index
  ==================================================*/

public class SuperArray implements ListInt {

    private int[] _data;
    private int _lastPos; // the position value of the last meaningful element
    private int _size; // the number of meaingful elements in your super array

    //default constructor
    //initializes 10-item array
    public SuperArray() {
        _data = new int[10];
        _lastPos = -1; //theres no meaningful element in the array yet
        _size = 0; //there are no meaningful elements in the array 
    }

    //output array in [a,b,c] format
    //eg, for int[] a = {1,2,3} ...
    //toString() -> "[1,2,3]"
    public String toString() {
        String output = "[ ";
        for ( int i : _data ) {
	    output += i + ", " ;
        }
        output = output.substring(0, output.length()-1) + " ]";
        return output;
    }

    //double capacity of this instance of SuperArray
    private void expand() {
        int[] _ndata = new int[ _data.length* 2 ];
        for (int i = 0; i < _data.length; i++) {
	    _ndata[i] = _data[i];
	}
	_data = _ndata; //because _ndata is local variable
    }


    //accessor method -- return value at specified index
    public int get( int index ) {
        return _data[index];
    }


    //mutator method -- set index to newVal, return old value at index
    public int set( int index, int newVal ) {
        int oldVal = _data[index]; //hold the value before it is changed
        _data[index] = newVal;
        return oldVal;
    }

    //adds an item after the last item
    public boolean add( int newVal ) {
	// int[] _ndata = new int[ _data.length + 1];
	// for (int i = 0; i < _data.length; i++) {
	//     _ndata[i] = _data[i];
	//     if ( i == _data.length + 1) _ndata[i] = newVal;
	//     _data = _ndata;
	// }

	if (_data.length -1 == _lastPos) { // if _lastPos is the last index value for the length of _data, then expand
	    expand();
	}
	_data[ _lastPos + 1 ] = newVal; 
	_lastPos++; //update _lastPos value because the index of the last meaningful element increased by 1
	_size++;//update _size because there is now 1 more meaningful element
	return true;
	    
    }

    /*only inserts an item at index within the range of SuperArray
     adding at at index shifts everything over to the right
      
     */
    public void add( int index, int newVal ) {
	if (index <= _data.length - 1 && index >= 0) {//only insert if the index is in the range of [0, _data.length -1]
	    
	    if (_data.length - 1 == _lastPos) {//if no space to insert, then expand
		expand();
	    }
	    for (int i = _lastPos + 1; i > index ; i--){
		_data[i] = _data[i-1];
	    }
	    _data[index] = newVal;
	    _lastPos++;
	    _size++;
	}
    }


    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public int remove( int index ) {
	int ans = -1;
	if (index < _data.length) {
	    ans = _data[index];
	    for (int i = index; i <= _lastPos; i++){ 
		_data[i] = _data[i + 1]; //move them one over to left
	        if (i == _lastPos){
		    _data[i] = 0;
		}
	    }
	    _lastPos--;
	    _size--;
	}
	return ans;
    }


    //return number of meaningful items in _data
    public int size() {
	return _size;
    }


    //main method for testing
    public static void main( String[] args ) {
  
        SuperArray curtis = new SuperArray();
        System.out.println( "Printing empty SuperArray curtis..." );
        System.out.println( curtis );

        for( int i = 0; i < curtis._data.length; i++ ) {
	    curtis.set( i, i * 2 );
	    curtis._size++;
        }

        System.out.println("Printing populated SuperArray curtis...");
        System.out.println(curtis);

        curtis.expand();
        System.out.println("Printing expanded SuperArray curtis...");
        System.out.println(curtis);

        for (int i = 0; i < curtis._data.length; i++) {
	    curtis.set (i , i * 2);
        }

        System.out.println("Printing repopulated SuperArray curtis...");
        System.out.println(curtis);

	SuperArray mayfield = new SuperArray();
	System.out.println("Printing empty SuperArray mayfield with 10 elements...");
	System.out.println(mayfield);

	mayfield.add(5);
	mayfield.add(4);
	mayfield.add(3);
	mayfield.add(2);
	mayfield.add(1);

	System.out.println("Printing populated SuperArray mayfield...");
	System.out.println(mayfield);

	mayfield.remove(3);
	System.out.println("Printing SuperArray mayfield post-removing value at third index...");
	System.out.println(mayfield);
	mayfield.remove(3);
	System.out.println("Printing SuperArray mayfield post-remove value at third index...");
	System.out.println(mayfield);

	mayfield.add(3,99);
	System.out.println("Printing SuperArray mayfield post-inserting 99 at third index ...");
	System.out.println(mayfield);
	mayfield.add(2,88);
	System.out.println("Printing SuperArray mayfield post-insert 88 at second index...");
	System.out.println(mayfield);
	mayfield.add(1,77);
	System.out.println("Printing SuperArray mayfield post-inserting 77 at first index...");
	System.out.println(mayfield);
	/*===========================================
	  ===========================================*/
    }

}//end class SuperArray
