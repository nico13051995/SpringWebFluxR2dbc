/*
 * This file is generated by jOOQ.
 */
package com.shs.codegen.maven.jooq.tables;


import com.shs.codegen.maven.jooq.Indexes;
import com.shs.codegen.maven.jooq.Keys;
import com.shs.codegen.maven.jooq.Public;
import com.shs.codegen.maven.jooq.tables.records.BcUserRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BcUser extends TableImpl<BcUserRecord> {

    private static final long serialVersionUID = -1058232672;

    /**
     * The reference instance of <code>public.bc_user</code>
     */
    public static final BcUser BC_USER = new BcUser();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BcUserRecord> getRecordType() {
        return BcUserRecord.class;
    }

    /**
     * The column <code>public.bc_user.id</code>.
     */
    public final TableField<BcUserRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('user_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.bc_user.name</code>.
     */
    public final TableField<BcUserRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(100), this, "");

    /**
     * Create a <code>public.bc_user</code> table reference
     */
    public BcUser() {
        this(DSL.name("bc_user"), null);
    }

    /**
     * Create an aliased <code>public.bc_user</code> table reference
     */
    public BcUser(String alias) {
        this(DSL.name(alias), BC_USER);
    }

    /**
     * Create an aliased <code>public.bc_user</code> table reference
     */
    public BcUser(Name alias) {
        this(alias, BC_USER);
    }

    private BcUser(Name alias, Table<BcUserRecord> aliased) {
        this(alias, aliased, null);
    }

    private BcUser(Name alias, Table<BcUserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> BcUser(Table<O> child, ForeignKey<O, BcUserRecord> key) {
        super(child, key, BC_USER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.USER_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<BcUserRecord, Long> getIdentity() {
        return Keys.IDENTITY_BC_USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<BcUserRecord> getPrimaryKey() {
        return Keys.USER_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<BcUserRecord>> getKeys() {
        return Arrays.<UniqueKey<BcUserRecord>>asList(Keys.USER_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BcUser as(String alias) {
        return new BcUser(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BcUser as(Name alias) {
        return new BcUser(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public BcUser rename(String name) {
        return new BcUser(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BcUser rename(Name name) {
        return new BcUser(name, null);
    }
}