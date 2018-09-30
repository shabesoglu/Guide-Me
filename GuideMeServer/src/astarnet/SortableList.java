package astarnet;


import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


public class SortableList {
    
    private ArrayList _List;
    private Comparator _Comparer;
    private boolean _UseObjectsComparison;
    private boolean _IsSorted;
    private boolean _KeepSorted;
    private boolean _AddDuplicates;
    private boolean _IsFixedSize;
    private boolean _IsReadOnly;
    private boolean _IsSynchronized;
    
    private Object _SyncRoot;
    
    public SortableList() {
        InitProperties(null, 0);
    }
    
    
    public Object getItems(int Index){	return _List.get(Index);}
    
    public int Add(Object O) {
        int Return = -1;
        if ( ObjectIsCompliant(O) ) {
            if ( _KeepSorted ) {
                int Index = IndexOf(O);
                int NewIndex = Index>=0 ? Index : -Index-1;
                if (NewIndex>=getCount()) _List.add(O);
                else _List.add(NewIndex, O);
                Return = NewIndex;
            } else {
                _IsSorted = false;
                _List.add(O);
                Return =_List.size();
            }
        }
        return Return;
    }
    
    
    private boolean ObjectIsCompliant(Object O) {
        
        if ( !_AddDuplicates && Contains(O) ) return false;
        return true;
    }
    
    public boolean Contains(Object O) {
        
        return _IsSorted ? Collections.binarySearch(_List,O, _Comparer)>=0 : _List.contains(O);
    }
    
    public int getCount() {return _List.size(); }
    
    public int IndexOf(Object O) {
        int Result = -1;
        if ( _IsSorted ) {
            Result = Collections.binarySearch(_List,O, _Comparer);
            while ( Result>0 && _List.get(Result-1).equals(O) ) Result--;
        } else Result = _List.indexOf(O);
        return Result;
    }
    
    
    
    public void Clear() { _List.clear(); }
    
    public void RemoveAt(int Index) { _List.remove(Index); }
    
    public int IndexOfMin() {
        int RetInt = -1;
        if ( _List.size()>0 ) {
            RetInt = 0;
            Object RetObj = _List.get(0);
            if ( !_IsSorted ) {
                for ( int i=1; i<_List.size(); i++ )
                    if ( _Comparer.compare(RetObj, _List.get(i))>0 ) {
                    RetObj = _List.get(i);
                    RetInt = i;
                    }
            }
        }
        return RetInt;
    }
    
    
    public int IndexOf(Object O, Equality AreEqual) {
        for (int i=0; i<_List.size(); i++)
            if ( AreEqual.AreEqual(_List.get(i), O) ) return i;
        return -1;
    }
    
    
    
    private void InitProperties(Comparator Comparer, int Capacity) {
        if ( Comparer!=null ) {
            _Comparer = Comparer;
            _UseObjectsComparison = false;
        } else {
            _Comparer = new Comparator() {
                public int compare(Object o1, Object o2) {
                    return Double.compare(((Track)o1).Evaluation(),((Track)o2).Evaluation());
                }
                public boolean equals(Object obj) {
                    return true;
                }
            };
            
            _UseObjectsComparison = true;
        }
        _List = Capacity>0 ? new ArrayList(Capacity) : new ArrayList();
        _IsSorted = true;
        _KeepSorted = true;
        _AddDuplicates = true;
    }
    
    
    
    
    
    
    
}



