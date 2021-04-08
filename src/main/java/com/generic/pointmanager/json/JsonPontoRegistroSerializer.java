package com.generic.pointmanager.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.generic.pointmanager.models.entity.PontoRegistro;

public class JsonPontoRegistroSerializer extends StdSerializer<PontoRegistro>{

	private static final long serialVersionUID = 6833550842371608593L;
	
	public JsonPontoRegistroSerializer() {
        this(null);
    }
	
	public JsonPontoRegistroSerializer(Class<PontoRegistro> t) {
		super(t);
	}

	@Override
	public void serialize(PontoRegistro value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("localPonto", value.getLocalPonto());
		gen.writeNumberField("numeroPonto", value.getNumeroPonto());
		gen.writeNumberField("usuario", value.getUsuario().getId());
		gen.writeEndObject();
	}

}
