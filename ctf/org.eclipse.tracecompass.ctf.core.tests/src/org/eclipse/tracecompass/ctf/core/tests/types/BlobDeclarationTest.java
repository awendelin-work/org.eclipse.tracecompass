/*******************************************************************************
 * Copyright (c) 2023 Ericsson
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sehr Moosabhoy - Initial implementation
 *******************************************************************************/

package org.eclipse.tracecompass.ctf.core.tests.types;

import static org.junit.Assert.assertNotNull;

import java.nio.ByteBuffer;
import java.util.HexFormat;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.tracecompass.ctf.core.CTFException;
import org.eclipse.tracecompass.ctf.core.event.io.BitBuffer;
import org.eclipse.tracecompass.ctf.core.event.scope.IDefinitionScope;
import org.eclipse.tracecompass.ctf.core.event.types.BlobDeclaration;
import org.eclipse.tracecompass.ctf.core.event.types.BlobDefinition;
import org.junit.Before;
import org.junit.Test;

/**
 * The class <code>BlobDeclarationTest</code> contains tests for the class
 * <code>{@link BlobDeclaration}</code>.
 *
 * @author Sehr Moosabhoy
 */
public class BlobDeclarationTest {

    private BlobDeclaration fixture;
    private static final int LENGTH = 16;
    @NonNull private static final String MEDIA_TYPE = "\"application/octet-stream\"";
    private static final byte[] UUID_ARRAY = HexFormat.of().parseHex("2a6422d06cee11e08c08cb07d7b3a564");

    /**
     * Perform pre-test initialization.
     */
    @Before
    public void setUp() {
        fixture = new BlobDeclaration(LENGTH, MEDIA_TYPE);
    }

    /**
     * Run the BlobDeclaration() constructor test.
     */
    @Test
    public void testBlobDeclaration() {
        BlobDeclaration result = new BlobDeclaration(LENGTH, MEDIA_TYPE);

        assertNotNull(result);
    }

    /**
     * Run the BlobDefinition createDefinition method test.
     *
     * @throws CTFException
     *             out of buffer exception
     */
    @Test
    public void testCreateDefinition() throws CTFException {
        IDefinitionScope definitionScope = null;
        String fieldName = "id";
        ByteBuffer allocate = ByteBuffer.allocate(16);
        BitBuffer bb = new BitBuffer(allocate);
        allocate.mark();
        allocate.put(UUID_ARRAY);
        allocate.reset();

        BlobDefinition result = fixture.createDefinition(definitionScope,
                fieldName, bb);

        assertNotNull(result);
    }
}
