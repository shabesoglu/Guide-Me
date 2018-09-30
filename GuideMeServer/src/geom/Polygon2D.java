package geom;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public abstract class Polygon2D implements Shape {
    
    private class PolygonIterator implements PathIterator {
        
        
        private double[] _coords;
        private int _index = 0;
        private boolean _done = false;
        
        public PolygonIterator( Polygon2D pl, AffineTransform at ) {
            int count = pl.getVertexCount() * 2;
            _coords = new double[count];
            if ( pl instanceof Polygon2D.Float ) {
                Polygon2D.Float f = (Polygon2D.Float)pl;
                if ( at == null || at.isIdentity() ) {
                    for ( int i = 0; i < count; i++ ) {
                        _coords[i] = f._coords[i];
                    }
                } else {
                    at.transform( f._coords, 0, _coords, 0, count / 2 );
                }
            } else {
                Polygon2D.Double d = (Polygon2D.Double)pl;
                if ( at == null || at.isIdentity() ) {
                    System.arraycopy( d._coords, 0, _coords, 0, count );
                } else {
                    at.transform( d._coords, 0, _coords, 0, count / 2 );
                }
            }
        }
        
        
        public int currentSegment( double coords[] ) {
            int type;
            
            if ( _index == _coords.length ) {
                if ( _done ) {
                    type = PathIterator.SEG_CLOSE;
                } else {
                    coords[0] = this._coords[0];
                    coords[1] = this._coords[1];
                    type = PathIterator.SEG_LINETO;
                }
            } else {
                coords[0] = this._coords[_index];
                coords[1] = this._coords[_index + 1];
                if ( _index == 0 ) {
                    type = PathIterator.SEG_MOVETO;
                } else {
                    type = PathIterator.SEG_LINETO;
                }
            }
            
            return type;
        }
        
        
        public int currentSegment( float coords[] ) {
            int type;
            
            if ( _index == _coords.length ) {
                if ( _done ) {
                    type = PathIterator.SEG_CLOSE;
                } else {
                    coords[0] = (float)this._coords[0];
                    coords[1] = (float)this._coords[1];
                    type = PathIterator.SEG_LINETO;
                }
            } else {
                coords[0] = (float)this._coords[_index];
                coords[1] = (float)this._coords[_index + 1];
                if ( _index == 0 ) {
                    type = PathIterator.SEG_MOVETO;
                } else {
                    type = PathIterator.SEG_LINETO;
                }
            }
            
            return type;
        }
        
        
        public int getWindingRule() {
            return PathIterator.WIND_NON_ZERO;
        }
        
        
        public boolean isDone() {
            return _done;
        }
        
        
        public void next() {
            if ( _index == _coords.length ) {
                _done = true;
            } else {
                _index += 2;
            }
        }
    }
    
    
    protected int _coordCount = 0;
    
    public int getCoordCount() {
        return _coordCount;
    }
    
    public abstract double[] getCoords();
    
    
    protected boolean _closed = false;
    
    
    public void closePath() {
        if ( getX( getVertexCount() - 1 ) == getX( 0 ) && getY( getVertexCount() - 1 ) == getY( 0 ) ) {
            _coordCount -= 2;
        }
        _closed = true;
    }
    
    
    public boolean contains( double x, double y ) {
        if ( !this.getBounds2D().contains( x, y ) )
            return false;
        
        int crossings = 0;
        if ( _coordCount == 0 ) {
            return false;
        }
        
        
        int i = 1;
        for ( ; i < getVertexCount(); ) {
            double x1 = getX( i - 1 );
            double x2 = getX( i );
            double y1 = getY( i - 1 );
            double y2 = getY( i );
            
            
            if ( x < x1 || x < x2 ) {
                if ( y == y2 ) {
                    crossings++;
                } else if ( y == y1 ) {
                    
                } else if ( Line2D.linesIntersect( x, y, Math.max( x1, x2 ), y, x1, y1, x2, y2 ) ) {
                    crossings++;
                }
            }
            i++;
        }
        
        double x1 = getX( i - 1 );
        double y1 = getY( i - 1 );
        double x2 = getX( 0 );
        double y2 = getY( 0 );
        
        
        if ( x < x1 || x < x2 ) {
            if ( Line2D.linesIntersect( x, y, Math.max( x1, x2 ), y, x1, y1, x2, y2 ) && ( y != y1 ) ) {
                crossings++;
            }
        }
        
        return crossings % 2 == 1;
    }
    
    
    public boolean contains( Point2D p ) {
        return contains( p.getX(), p.getY() );
    }
    
    public boolean contains( Rectangle2D r ) {
        return contains( r.getX(), r.getY(), r.getWidth(), r.getHeight() );
    }
    
    public boolean contains( double x1, double y1, double w, double h ) {
        double x2 = x1 + w;
        double y2 = y1 + h;
        return contains( x1, y1 ) && contains( x1, y2 ) && contains( x2, y1 ) && contains( x2, y2 );
    }
    
    
    public Rectangle getBounds() {
        return getBounds2D().getBounds();
    }
    
    
    public abstract Rectangle2D getBounds2D();
    
    
    public PathIterator getPathIterator( AffineTransform at, double flatness ) {
        return getPathIterator( at );
    }
    
    
    public PathIterator getPathIterator( AffineTransform at ) {
        return new PolygonIterator( this, at );
    }
    
    
    public int getVertexCount() {
        return _coordCount / 2;
    }
    
    
    public abstract double getX( int index );
    
    
    
    public abstract double getY( int index );
    
    
    public boolean intersects( Rectangle2D r ) {
        return intersects( r.getX(), r.getY(), r.getWidth(), r.getHeight() );
    }
    
    
    public boolean intersects( double x1, double y1, double w, double h ) {
        double x2 = x1 + w;
        double y2 = y1 + h;
        
        if ( _coordCount == 0 ) {
            return false;
        }
        
        
        Rectangle2D rect = new Rectangle2D.Double( x1, y1, w, h );
        if ( !getBounds().intersects( rect ) )
            return false;
        
        
        if ( contains( x1, y1 ) || contains( x1, y2 ) || contains( x2, y1 ) || contains( x2, y2 ) )
            return true;
        
        
        for ( int i = 0; i < getVertexCount(); i++ ) {
            if ( rect.contains( getX( i ), getY( i ) ) )
                return true;
        }
        
        
        for ( int i = 1; i < getVertexCount(); i++ ) {
            double vx1 = getX( i - 1 );
            double vx2 = getX( i );
            double vy1 = getY( i - 1 );
            double vy2 = getY( i );
            
            if ( Line2D.linesIntersect( x1, y1, x1, y2, vx1, vy1, vx2, vy2 ) ) {
                return true;
            }
            if ( Line2D.linesIntersect( x1, y2, x2, y2, vx1, vy1, vx2, vy2 ) ) {
                return true;
            }
            if ( Line2D.linesIntersect( x2, y2, x2, y1, vx1, vy1, vx2, vy2 ) ) {
                return true;
            }
            if ( Line2D.linesIntersect( x2, y1, x1, y1, vx1, vy1, vx2, vy2 ) ) {
                return true;
            }
        }
        return false;
    }
    
    
    public abstract void lineTo( double x, double y );
    
    
    public abstract void moveTo( double x, double y );
    
    public void reset() {
        _coordCount = 0;
        _closed = false;
    }
    
    
    public abstract void setX( int index, double x );
    
    
    public abstract void setY( int index, double y );
    
    
    public abstract void transform( AffineTransform at );
    
    
    public abstract void translate( double x, double y );
    
    
    @Override
    public String toString() {
        String out = getClass().getName() + "[\n";
        for ( int i = 0; i < getVertexCount(); i++ ) {
            out = out + "\t" + getX( i ) + ", " + getY( i ) + "\n";
        }
        out = out + "]";
        return out;
    }
    
    
    public static class Float extends Polygon2D {
        
        
        float _coords[];
        
        @Override
        public double[] getCoords() {
            float[] f = _coords.clone();
            
            double[] c = new double[f.length];
            
            for ( int i = 0; i < f.length; i++ ) {
                c[i] = f[i];
            }
            
            return c;
        }
        
        
        public Float( int size ) {
            _coords = new float[2 * size];
        }
        
        
        public Float( float[] coords ) {
            _coords = coords;
            _coordCount = coords.length;
        }
        
        public Float( float[] xPoints, float[] yPoints, int nPoints ) {
            _coords = new float[2 * nPoints];
            
            for ( int i = 0; i < nPoints; i++ ) {
                _coords[i * 2] = xPoints[i];
                _coords[i * 2 + 1] = yPoints[i];
            }
            
            _coordCount = _coords.length;
        }
        
        
        public Float() {
            this( 2 );
        }
        
        public Float( float x, float y ) {
            this( 2 );
            _coords[0] = x;
            _coords[1] = y;
            _coordCount = 2;
        }
        
        
        @Override
        public Rectangle2D getBounds2D() {
            if ( _coordCount <= 1 ) {
                return new Rectangle2D.Float();
            }
            float x1 = _coords[0];
            float y1 = _coords[1];
            float x2 = x1;
            float y2 = y1;
            for ( int i = 2; i < _coordCount; ) {
                if ( _coords[i] < x1 ) {
                    x1 = _coords[i];
                } else if ( _coords[i] > x2 ) {
                    x2 = _coords[i];
                }
                i++;
                if ( _coords[i] < y1 ) {
                    y1 = _coords[i];
                } else if ( _coords[i] > y2 ) {
                    y2 = _coords[i];
                }
                i++;
            }
            return new Rectangle2D.Float( x1, y1, x2 - x1, y2 - y1 );
        }
        
        
        @Override
        public double getX( int index ) {
            return _coords[index * 2];
        }
        
        
        @Override
        public double getY( int index ) {
            return _coords[index * 2 + 1];
        }
        
        
        
        public float  getFX( int index ) {
            return _coords[index * 2];
        }
        
        
        public float getFY( int index ) {
            return _coords[index * 2 + 1];
        }
        
        
        @Override
        public void lineTo( double x, double y ) {
            if ( _closed ) {
                throw new UnsupportedOperationException( "This polygon has already been closed" );
            }
            if ( _coordCount == _coords.length ) {
                float temp[] = new float[_coordCount * 2];
                System.arraycopy( _coords, 0, temp, 0, _coordCount );
                _coords = temp;
            }
            _coords[_coordCount++] = (float)x;
            _coords[_coordCount++] = (float)y;
        }
        
        
        @Override
        public void moveTo( double x, double y ) {
            if ( _coordCount > 0 ) {
                throw new UnsupportedOperationException( "This polygon already has vertices" );
            }
            _coords[0] = (float)x;
            _coords[1] = (float)y;
            _coordCount = 2;
        }
        
        
        @Override
        public void setX( int index, double x ) {
            _coords[index * 2] = (float)x;
        }
        
        
        @Override
        public void setY( int index, double y ) {
            _coords[index * 2 + 1] = (float)y;
        }
        
        
        @Override
        public void transform( AffineTransform at ) {
            at.transform( _coords, 0, _coords, 0, _coordCount / 2 );
        }
        
        
        @Override
        public void translate( double x, double y ) {
            float fx = (float)x;
            float fy = (float)y;
            for ( int i = 0; i < _coordCount; ) {
                _coords[i++] += fx;
                _coords[i++] += fy;
            }
        }
        
    }
    
    
    public static class Double extends Polygon2D {
        
        
        double _coords[];
        
        @Override
        public double[] getCoords() {
            return _coords.clone();
        }
        
        
        public Double( int size ) {
            _coords = new double[2 * size];
        }
        
        public Double( double[] coords ) {
            _coords = coords;
            _coordCount = coords.length;
        }
        
        
        public Double( double[] xPoints, double[] yPoints, int nPoints ) {
            _coords = new double[2 * nPoints];
            
            for ( int i = 0; i < nPoints; i++ ) {
                _coords[i * 2] = xPoints[i];
                _coords[i * 2 + 1] = yPoints[i];
            }
            
            _coordCount = _coords.length;
        }
        
        
        public Double() {
            this( 2 );
        }
        
        
        public Double( double x, double y ) {
            this( 2 );
            _coords[0] = x;
            _coords[1] = y;
            _coordCount = 2;
        }
        
        
        @Override
        public Rectangle2D getBounds2D() {
            if ( _coordCount <= 0 ) {
                return new Rectangle2D.Double();
            }
            double x1 = _coords[0];
            double y1 = _coords[1];
            double x2 = x1;
            double y2 = y1;
            for ( int i = 2; i < _coordCount; ) {
                if ( _coords[i] < x1 ) {
                    x1 = _coords[i];
                } else if ( _coords[i] > x2 ) {
                    x2 = _coords[i];
                }
                i++;
                if ( _coords[i] < y1 ) {
                    y1 = _coords[i];
                } else if ( _coords[i] > y2 ) {
                    y2 = _coords[i];
                }
                i++;
            }
            return new Rectangle2D.Double( x1, y1, x2 - x1, y2 - y1 );
        }
        
        
        @Override
        public int getVertexCount() {
            return _coordCount / 2;
        }
        
        
        @Override
        public double getX( int index ) {
            return _coords[index * 2];
        }
        
        
        @Override
        public double getY( int index ) {
            return _coords[index * 2 + 1];
        }
        
        
        @Override
        public void lineTo( double x, double y ) {
            if ( _coordCount == _coords.length ) {
                double temp[] = new double[_coordCount * 2];
                System.arraycopy( _coords, 0, temp, 0, _coordCount );
                _coords = temp;
            }
            _coords[_coordCount++] = x;
            _coords[_coordCount++] = y;
        }
        
        
        @Override
        public void moveTo( double x, double y ) {
            if ( _coordCount > 0 ) {
                throw new UnsupportedOperationException( "This polygon already has vertices" );
            }
            _coords[0] = x;
            _coords[1] = y;
            _coordCount = 2;
        }
        
        
        @Override
        public void setX( int index, double x ) {
            _coords[index * 2] = x;
        }
        
        
        @Override
        public void setY( int index, double y ) {
            _coords[index * 2 + 1] = y;
        }
        
        
        @Override
        public void transform( AffineTransform at ) {
            at.transform( _coords, 0, _coords, 0, _coordCount / 2 );
        }
        
        
        @Override
        public void translate( double x, double y ) {
            for ( int i = 0; i < _coordCount; ) {
                _coords[i++] += x;
                _coords[i++] += y;
            }
        }
        
        
    }
}
