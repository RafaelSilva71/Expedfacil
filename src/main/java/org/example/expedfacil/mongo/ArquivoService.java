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

import java.io.IOException;

@Service
public class ArquivoService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    public String salvarArquivo(MultipartFile file, String descricao) throws IOException {
        DBObject metaData = new BasicDBObject();
        metaData.put("descricao", descricao);
        metaData.put("tipo", file.getContentType());

        ObjectId id = gridFsTemplate.store(
                file.getInputStream(),
                file.getOriginalFilename(),
                file.getContentType(),
                metaData
        );

        System.out.println("Arquivo salvo com ID: " + id.toString());

        return id.toString();
    }

    public GridFsResource buscarArquivoPorId(String id) {
        return gridFsTemplate.getResource(
                gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(new ObjectId(id))))
        );
    }
}
