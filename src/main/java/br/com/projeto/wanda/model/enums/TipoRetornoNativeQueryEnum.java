package br.com.projeto.wanda.model.enums;

import org.hibernate.type.*;

import lombok.Getter;

@Getter
public enum TipoRetornoNativeQueryEnum {

	LONG(1L, StandardBasicTypes.LONG),
	INTEGER(2L,StandardBasicTypes.INTEGER),
	STRING(3L, StandardBasicTypes.STRING),
	BIGDECIMAL(4L, StandardBasicTypes.BIG_DECIMAL),
	DOUBLE(5L, StandardBasicTypes.DOUBLE),
	FLOAT(6L, StandardBasicTypes.FLOAT),
	BYTE(7L, StandardBasicTypes.BYTE),
	BINARIO(8L, StandardBasicTypes.BINARY),
	BLOB(9L, StandardBasicTypes.BLOB),
	CALENDAR(10L, StandardBasicTypes.CALENDAR),
	CALENDAR_DATE(11L, StandardBasicTypes.CALENDAR_DATE),
	CHAR_ARRAY(12L, StandardBasicTypes.CHAR_ARRAY),
	DATE(12L, StandardBasicTypes.DATE),
	TIMESTAMP(13L, StandardBasicTypes.TIMESTAMP),
	CURRENCY(14L, StandardBasicTypes.CURRENCY),
	CLOB(14L, StandardBasicTypes.CLOB),
	BIG_INTEGER(14L, StandardBasicTypes.BIG_INTEGER),
	BOOLEAN(15L, StandardBasicTypes.BOOLEAN);

	private long codigo;
	private Type type;

	TipoRetornoNativeQueryEnum(long codigo, BigIntegerType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, ClobType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, CurrencyType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, TimestampType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, BooleanType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, DateType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, CharArrayType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, CalendarDateType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, CalendarType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, BlobType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, BinaryType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, BigDecimalType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, FloatType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, DoubleType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, IntegerType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, ByteType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, LongType type) {
		this.codigo = codigo;
		this.type = type;
	}
	TipoRetornoNativeQueryEnum(long codigo, StringType type) {
		this.codigo = codigo;
		this.type = type;
	}

	public static TipoRetornoNativeQueryEnum getTipoRetorno(long codigo) {
		for(TipoRetornoNativeQueryEnum tipoRetorno: values())
			if(codigo == tipoRetorno.getCodigo())
				return tipoRetorno;
				
		return null;
	}
}
