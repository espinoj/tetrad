///////////////////////////////////////////////////////////////////////////////
// For information as to what this class does, see the Javadoc, below.       //
// Copyright (C) 1998, 1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006,       //
// 2007, 2008, 2009, 2010, 2014, 2015 by Peter Spirtes, Richard Scheines, Joseph   //
// Ramsey, and Clark Glymour.                                                //
//                                                                           //
// This program is free software; you can redistribute it and/or modify      //
// it under the terms of the GNU General Public License as published by      //
// the Free Software Foundation; either version 2 of the License, or         //
// (at your option) any later version.                                       //
//                                                                           //
// This program is distributed in the hope that it will be useful,           //
// but WITHOUT ANY WARRANTY; without even the implied warranty of            //
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the             //
// GNU General Public License for more details.                              //
//                                                                           //
// You should have received a copy of the GNU General Public License         //
// along with this program; if not, write to the Free Software               //
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA //
///////////////////////////////////////////////////////////////////////////////

package edu.cmu.tetradapp.model;

import edu.cmu.tetrad.util.Params;
import edu.cmu.tetrad.util.TetradSerializable;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Stores the parameters needed to initialize a BayesPm.
 *
 * @author Joseph Ramsey jdramsey@andrew.cmu.edu
 */
public class BayesImParams implements Params, TetradSerializable {
    static final long serialVersionUID = 23L;

    /**
     * The initialization mode in which probability values in tables are
     * retained where possible and otherwise filled in manually.
     */
    public static final int MANUAL_RETAIN = 0;

    /**
     * The initialization mode in which probability values in tables are
     * retained where possible and otherwise filled in manually.
     */
    public static final int RANDOM_RETAIN = 1;

    /**
     * The initialization mode in which probability values in tables are
     * completely reinitialized using random values.
     */
    public static final int RANDOM_OVERWRITE = 2;

    /**
     * @serial Must be MANUAL_RETAIN, MANUAL_RETAIN, or RANDOM_OVERWRITE.
     */
    private int initializationMode = MANUAL_RETAIN;

    //============================CONSTRUCTORS============================//

    /**
     * Constructs a new parameters object. Must be a blank constructor.
     */
    public BayesImParams() {
    }

    /**
     * Generates a simple exemplar of this class to test serialization.
     *
     * @see edu.cmu.TestSerialization
     * @see edu.cmu.tetradapp.util.TetradSerializableUtils
     */
    public static BayesImParams serializableInstance() {
        return new BayesImParams();
    }

    //============================PUBLIC METHODS==========================//

    public int getInitializationMode() {
        return initializationMode;
    }

    public void setInitializationMode(int initializationMode) {
        if (initializationMode != MANUAL_RETAIN &&
                initializationMode != RANDOM_RETAIN &&
                initializationMode != RANDOM_OVERWRITE) {
            throw new IllegalArgumentException(
                    "Unrecognized initialization " + "mode.");
        }

        this.initializationMode = initializationMode;
    }

    /**
     * Adds semantic checks to the default deserialization method. This method
     * must have the standard signature for a readObject method, and the body of
     * the method must begin with "s.defaultReadObject();". Other than that, any
     * semantic checks can be specified and do not need to stay the same from
     * version to version. A readObject method of this form may be added to any
     * class, even if Tetrad sessions were previously saved out using a version
     * of the class that didn't include it. (That's what the
     * "s.defaultReadObject();" is for. See J. Bloch, Effective Java, for help.
     *
     * @throws java.io.IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream s)
            throws IOException, ClassNotFoundException {
        s.defaultReadObject();

        switch (initializationMode) {
            case MANUAL_RETAIN:
                // Falls through.
            case RANDOM_RETAIN:
                // Falls through.
            case RANDOM_OVERWRITE:
                break;
            default:
                throw new IllegalStateException(
                        "Illegal value: " + initializationMode);
        }
    }
}






