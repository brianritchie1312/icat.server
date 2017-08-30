package org.icatproject.exposed;

import java.io.ByteArrayOutputStream;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.icatproject.core.IcatException;
import org.icatproject.core.IcatException.IcatExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class IcatExceptionMapper implements ExceptionMapper<IcatException> {

	private static Logger logger = LoggerFactory.getLogger(IcatExceptionMapper.class);

	@Override
	public Response toResponse(IcatException e) {

		IcatExceptionType type = e.getType();
		if (type == IcatExceptionType.INTERNAL) {
			logger.error("Processing", e);
		} else {
			logger.info("Processing {}: {}", type, e.getMessage());
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JsonGenerator gen = Json.createGenerator(baos);
		gen.writeStartObject().write("code", e.getType().name()).write("message", e.getMessage());
		if (e.getOffset() >= 0) {
			gen.write("offset", e.getOffset());
			logger.debug("Offset {}", e.getOffset());
		}
		gen.writeEnd().close();
		return Response.status(e.getType().getStatus()).entity(baos.toString()).build();

	}
}