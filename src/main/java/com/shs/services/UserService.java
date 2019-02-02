package com.shs.services;

import com.shs.codegen.maven.jooq.tables.BcUser;
import com.shs.codegen.maven.jooq.tables.records.BcUserRecord;
import com.shs.dto.BcUserDTO;
import org.jooq.DSLContext;
import org.jooq.InsertValuesStepN;
import org.jooq.Loader;
import org.jooq.LoaderError;
import org.modelmapper.ModelMapper;
import org.modelmapper.jooq.RecordValueReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    RecordModelMapperService recordMapperService;

    @Autowired
    DSLContext pJooq;

    public List<BcUserDTO> findAll(){
        List<BcUserRecord> records = pJooq.selectFrom(BcUser.BC_USER).fetch().into(BcUserRecord.class);
        List<BcUserDTO> dtoList = recordMapperService.mapAll(records,BcUserDTO.class);
        return dtoList;
    }

    public int saveAll(List<BcUserDTO> users){
        List<BcUserRecord> records = recordMapperService.mapAll(users,BcUserRecord.class);
        Loader loader;
        try {
            loader = pJooq.loadInto(BcUser.BC_USER).loadRecords(records).fields(BcUser.BC_USER.fields()).execute();
            if(loader.errors().size() > 0){
                LoaderError loaderError = (LoaderError) loader.errors().get(0);
                throw loaderError.exception();
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
            loader = null;
        }
        return loader == null ? 0 : loader.processed();
        /*int insertedValuesCount = pJooq.insertInto(BcUser.BC_USER)
                .values(users)
                .execute();
         return insertedValuesCount;*/
    }
}
