package com.jentsch.mapping;

import co.elastic.clients.elasticsearch._types.mapping.*;

import java.util.HashMap;
import java.util.Map;
//https://github.com/apache/james-project/blob/master/mailbox/opensearch/src/main/java/org/apache/james/mailbox/opensearch/MailboxMappingFactory.java
public class MailboxMappingFactory2 {
    public static final String CASE_INSENSITIVE = "case_insensitive";
    public static final String KEEP_MAIL_AND_URL = "keep_mail_and_url";
    public static final String BOOLEAN = "boolean";
    public static final String TYPE = "type";
    public static final String LONG = "long";
    public static final String DOUBLE = "double";
    public static final String KEYWORD = "keyword";
    public static final String PROPERTIES = "properties";
    public static final String ROUTING = "_routing";
    public static final String REQUIRED = "required";
    public static final String DATE = "date";
    public static final String FORMAT = "format";
    public static final String NESTED = "nested";
    public static final String FIELDS = "fields";
    public static final String RAW = "raw";
    public static final String ANALYZER = "analyzer";
    public static final String TOKENIZER = "tokenizer";
    public static final String NORMALIZER = "normalizer";
    public static final String SEARCH_ANALYZER = "search_analyzer";
    private static final String STANDARD = "standard";
    private static final String SIMPLE = "simple";

    public static TypeMapping getMappingContent() {
        return new TypeMapping.Builder()
                .dynamic(DynamicMapping.Strict)
                .routing(new RoutingField.Builder()
                        .required(true)
                        .build())
                .properties(generateProperties())
                .build();
    }

    private static Map<String, Property> generateProperties() {
        return new HashMap<String, Property>() {
            {
                put(JsonMessageConstants.MESSAGE_ID, new Property.Builder()
                        .keyword(new KeywordProperty.Builder().store(true).build()).build());
                put(JsonMessageConstants.MESSAGE_ID, new Property.Builder()
                        .keyword(new KeywordProperty.Builder().store(true).build()).build());
                put(JsonMessageConstants.THREAD_ID, new Property.Builder()
                        .keyword(new KeywordProperty.Builder().build()).build());
                put(JsonMessageConstants.UID, new Property.Builder()
                        .long_(new LongNumberProperty.Builder().store(true).build()).build());
                put(JsonMessageConstants.MODSEQ, new Property.Builder()
                        .long_(new LongNumberProperty.Builder().build()).build());
                put(JsonMessageConstants.SIZE, new Property.Builder()
                        .long_(new LongNumberProperty.Builder().build()).build());
                put(JsonMessageConstants.IS_ANSWERED, new Property.Builder()
                        .boolean_(new BooleanProperty.Builder().build()).build());
                put(JsonMessageConstants.IS_DELETED, new Property.Builder()
                        .boolean_(new BooleanProperty.Builder().build()).build());
                put(JsonMessageConstants.IS_DRAFT, new Property.Builder()
                        .boolean_(new BooleanProperty.Builder().build())
                        .build());
                put(JsonMessageConstants.IS_FLAGGED, new Property.Builder()
                        .boolean_(new BooleanProperty.Builder().build())
                        .build());
                put(JsonMessageConstants.IS_RECENT, new Property.Builder()
                        .boolean_(new BooleanProperty.Builder().build())
                        .build());
                put(JsonMessageConstants.IS_UNREAD, new Property.Builder()
                        .boolean_(new BooleanProperty.Builder().build())
                        .build());
                put(JsonMessageConstants.DATE, new Property.Builder()
                        .date(new DateProperty.Builder()
                                .format("uuuu-MM-dd'T'HH:mm:ssX||uuuu-MM-dd'T'HH:mm:ssXXX||uuuu-MM-dd'T'HH:mm:ssXXXXX")
                                .build())
                        .build());
                put(JsonMessageConstants.SENT_DATE, new Property.Builder()
                        .date(new DateProperty.Builder()
                                .format("uuuu-MM-dd'T'HH:mm:ssX||uuuu-MM-dd'T'HH:mm:ssXXX||uuuu-MM-dd'T'HH:mm:ssXXXXX")
                                .build())
                        .build());
                put(JsonMessageConstants.SAVE_DATE, new Property.Builder()
                        .date(new DateProperty.Builder()
                                .format("uuuu-MM-dd'T'HH:mm:ssX||uuuu-MM-dd'T'HH:mm:ssXXX||uuuu-MM-dd'T'HH:mm:ssXXXXX")
                                .build())
                        .build());
                put(JsonMessageConstants.USER_FLAGS, new Property.Builder()
                        .keyword(new KeywordProperty.Builder().normalizer(CASE_INSENSITIVE).build())
                        .build());
                put(JsonMessageConstants.MEDIA_TYPE, new Property.Builder()
                        .keyword(new KeywordProperty.Builder().build())
                        .build());
                put(JsonMessageConstants.SUBTYPE, new Property.Builder()
                        .keyword(new KeywordProperty.Builder().build())
                        .build());
                put(JsonMessageConstants.FROM, new Property.Builder()
                        .object(new ObjectProperty.Builder()
                                .properties(Map.of(
                                        JsonMessageConstants.EMailer.NAME, new Property.Builder()
                                                .text(new TextProperty.Builder().analyzer(KEEP_MAIL_AND_URL).build())
                                                .build(),
                                        JsonMessageConstants.EMailer.DOMAIN, new Property.Builder()
                                                .text(new TextProperty.Builder()
                                                        .analyzer(SIMPLE)
                                                        .searchAnalyzer(KEYWORD)
                                                        .build())
                                                .build(),
                                        JsonMessageConstants.EMailer.ADDRESS, new Property.Builder()
                                                .text(new TextProperty.Builder()
                                                        .analyzer(STANDARD)
                                                        .searchAnalyzer(KEEP_MAIL_AND_URL)
                                                        .fields(RAW, new Property.Builder()
                                                                .keyword(new KeywordProperty.Builder().normalizer(CASE_INSENSITIVE).build())
                                                                .build())
                                                        .build())
                                                .build()
                                ))
                                .build())
                        .build());
                put(JsonMessageConstants.HEADERS, new Property.Builder()
                        .nested(new NestedProperty.Builder()
                                .properties(Map.of(
                                        JsonMessageConstants.HEADER.NAME, new Property.Builder()
                                                .keyword(new KeywordProperty.Builder().build())
                                                .build(),
                                        JsonMessageConstants.HEADER.VALUE, new Property.Builder()
                                                .text(new TextProperty.Builder().analyzer(KEEP_MAIL_AND_URL).build())
                                                .build()
                                ))
                                .build())
                        .build());

                put(JsonMessageConstants.SUBJECT, new Property.Builder()
                        .text(new TextProperty.Builder()
                                .analyzer(KEEP_MAIL_AND_URL)
                                .fields(RAW, new Property.Builder()
                                        .keyword(new KeywordProperty.Builder().normalizer(CASE_INSENSITIVE).build())
                                        .build())
                                .build())
                        .build());
                put(JsonMessageConstants.TO, new Property.Builder()
                        .object(new ObjectProperty.Builder()
                                .properties(Map.of(
                                        JsonMessageConstants.EMailer.NAME, new Property.Builder()
                                                .text(new TextProperty.Builder().analyzer(KEEP_MAIL_AND_URL).build())
                                                .build(),
                                        JsonMessageConstants.EMailer.DOMAIN, new Property.Builder()
                                                .text(new TextProperty.Builder()
                                                        .analyzer(SIMPLE)
                                                        .searchAnalyzer(KEYWORD)
                                                        .build())
                                                .build(),
                                        JsonMessageConstants.EMailer.ADDRESS, new Property.Builder()
                                                .text(new TextProperty.Builder()
                                                        .analyzer(STANDARD)
                                                        .searchAnalyzer(KEEP_MAIL_AND_URL)
                                                        .fields(RAW, new Property.Builder()
                                                                .keyword(new KeywordProperty.Builder().normalizer(CASE_INSENSITIVE).build())
                                                                .build())
                                                        .build())
                                                .build()
                                ))
                                .build())
                        .build());
                put(JsonMessageConstants.CC, new Property.Builder()
                        .object(new ObjectProperty.Builder()
                                .properties(Map.of(
                                        JsonMessageConstants.EMailer.NAME, new Property.Builder()
                                                .text(new TextProperty.Builder().analyzer(KEEP_MAIL_AND_URL).build())
                                                .build(),
                                        JsonMessageConstants.EMailer.DOMAIN, new Property.Builder()
                                                .text(new TextProperty.Builder()
                                                        .analyzer(SIMPLE)
                                                        .searchAnalyzer(KEYWORD)
                                                        .build())
                                                .build(),
                                        JsonMessageConstants.EMailer.ADDRESS, new Property.Builder()
                                                .text(new TextProperty.Builder()
                                                        .analyzer(STANDARD)
                                                        .searchAnalyzer(KEEP_MAIL_AND_URL)
                                                        .fields(RAW, new Property.Builder()
                                                                .keyword(new KeywordProperty.Builder().normalizer(CASE_INSENSITIVE).build())
                                                                .build())
                                                        .build())
                                                .build()
                                ))
                                .build())
                        .build());
                put(JsonMessageConstants.BCC, new Property.Builder()
                        .object(new ObjectProperty.Builder()
                                .properties(Map.of(
                                        JsonMessageConstants.EMailer.NAME, new Property.Builder()
                                                .text(new TextProperty.Builder().analyzer(KEEP_MAIL_AND_URL).build())
                                                .build(),
                                        JsonMessageConstants.EMailer.DOMAIN, new Property.Builder()
                                                .text(new TextProperty.Builder()
                                                        .analyzer(SIMPLE)
                                                        .searchAnalyzer(KEYWORD)
                                                        .build())
                                                .build(),
                                        JsonMessageConstants.EMailer.ADDRESS, new Property.Builder()
                                                .text(new TextProperty.Builder()
                                                        .analyzer(STANDARD)
                                                        .searchAnalyzer(KEEP_MAIL_AND_URL)
                                                        .fields(RAW, new Property.Builder()
                                                                .keyword(new KeywordProperty.Builder().normalizer(CASE_INSENSITIVE).build())
                                                                .build())
                                                        .build())
                                                .build()
                                ))
                                .build())
                        .build());
                put(JsonMessageConstants.MAILBOX_ID, new Property.Builder()
                        .keyword(new KeywordProperty.Builder().store(true).build())
                        .build());
                put(JsonMessageConstants.MIME_MESSAGE_ID, new Property.Builder()
                        .keyword(new KeywordProperty.Builder().build())
                        .build());
                put(JsonMessageConstants.TEXT_BODY, new Property.Builder()
                        .text(new TextProperty.Builder().analyzer(STANDARD).build())
                        .build());
                put(JsonMessageConstants.HTML_BODY, new Property.Builder()
                        .text(new TextProperty.Builder().analyzer(STANDARD).build())
                        .build());
                put(JsonMessageConstants.HAS_ATTACHMENT, new Property.Builder()
                        .boolean_(new BooleanProperty.Builder().build())
                        .build());
                put(JsonMessageConstants.ATTACHMENTS, new Property.Builder()
                        .object(new ObjectProperty.Builder()
                                .properties(Map.of(
                                        JsonMessageConstants.Attachment.FILENAME, new Property.Builder()
                                                .text(new TextProperty.Builder().analyzer(STANDARD).build())
                                                .build(),
                                        JsonMessageConstants.Attachment.TEXT_CONTENT, new Property.Builder()
                                                .text(new TextProperty.Builder().analyzer(STANDARD).build())
                                                .build(),
                                        JsonMessageConstants.Attachment.MEDIA_TYPE, new Property.Builder()
                                                .keyword(new KeywordProperty.Builder().build())
                                                .build(),
                                        JsonMessageConstants.Attachment.SUBTYPE, new Property.Builder()
                                                .keyword(new KeywordProperty.Builder().build())
                                                .build(),
                                        JsonMessageConstants.Attachment.FILE_EXTENSION, new Property.Builder()
                                                .keyword(new KeywordProperty.Builder().build())
                                                .build(),
                                        JsonMessageConstants.Attachment.CONTENT_DISPOSITION, new Property.Builder()
                                                .keyword(new KeywordProperty.Builder().build())
                                                .build()
                                ))
                                .build())
                        .build());
            }
        };
    }
}
