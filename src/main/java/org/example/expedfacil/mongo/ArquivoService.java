package org.example.expedfacil.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import com.mongodb.client.gridfs.model.GridFSFile;

import java.io.IOException;

@Service
public class ArquivoService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations gridFsOperations;

    public String salvarNotaFiscalComNumeroEmbarque(MultipartFile file, String numeroEmbarque) throws IOException {
        DBObject metaData = new BasicDBObject();
        metaData.put("numeroEmbarque", numeroEmbarque);
        metaData.put("tipo", file.getContentType());

        ObjectId id = gridFsTemplate.store(
                file.getInputStream(),
                file.getOriginalFilename(),
                file.getContentType(),
                metaData
        );

        return id.toString();
    }

    public GridFsResource buscarArquivoPorId(String id) {
        GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(new ObjectId(id))));
        return file != null ? gridFsOperations.getResource(file) : null;
    }

    public GridFsResource buscarNotaFiscalPorNumeroEmbarque(String numeroEmbarque) {
        Query query = new Query(Criteria.where("metadata.numeroEmbarque").is(numeroEmbarque));
        GridFSFile file = gridFsTemplate.findOne(query);
        return file != null ? gridFsOperations.getResource(file) : null;
    }
}
