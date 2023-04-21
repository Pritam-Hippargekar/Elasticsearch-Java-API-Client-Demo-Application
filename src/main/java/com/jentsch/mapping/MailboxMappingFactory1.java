//package com.jentsch.mapping;

//import java.io.IOException;
//https://github.com/apache/james-project/blob/master/backends-common/opensearch/src/main/java/org/apache/james/backends/opensearch/IndexCreationFactory.java
//https://github.com/apache/james-project/blob/ef8f4fe63ca3aafd5c8af18fa1a006a6d7176517/mailbox/opensearch/src/main/java/org/apache/james/mailbox/opensearch/MailboxMappingFactory.java
//public class MailboxMappingFactory {
//    private static final String STANDARD = "standard";
//    private static final String SIMPLE = "simple";
//    private static final String STORE = "store";
//
//    public static XContentBuilder getMappingContent() {
//        try {
//            return jsonBuilder()
//                    .startObject()
//
//                    .field("dynamic", "strict")
//
//                    .startObject(ROUTING)
//                    .field(REQUIRED, true)
//                    .endObject()
//
//                    .startObject(PROPERTIES)
//
//                    .startObject(JsonMessageConstants.MESSAGE_ID)
//                    .field(TYPE, KEYWORD)
//                    .field(STORE, true)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.THREAD_ID)
//                    .field(TYPE, KEYWORD)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.UID)
//                    .field(TYPE, LONG)
//                    .field(STORE, true)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.MODSEQ)
//                    .field(TYPE, LONG)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.SIZE)
//                    .field(TYPE, LONG)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.IS_ANSWERED)
//                    .field(TYPE, BOOLEAN)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.IS_DELETED)
//                    .field(TYPE, BOOLEAN)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.IS_DRAFT)
//                    .field(TYPE, BOOLEAN)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.IS_FLAGGED)
//                    .field(TYPE, BOOLEAN)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.IS_RECENT)
//                    .field(TYPE, BOOLEAN)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.IS_UNREAD)
//                    .field(TYPE, BOOLEAN)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.DATE)
//                    .field(TYPE, IndexCreationFactory.DATE)
//                    .field(FORMAT, "uuuu-MM-dd'T'HH:mm:ssX||uuuu-MM-dd'T'HH:mm:ssXXX||uuuu-MM-dd'T'HH:mm:ssXXXXX")
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.SENT_DATE)
//                    .field(TYPE, IndexCreationFactory.DATE)
//                    .field(FORMAT, "uuuu-MM-dd'T'HH:mm:ssX||uuuu-MM-dd'T'HH:mm:ssXXX||uuuu-MM-dd'T'HH:mm:ssXXXXX")
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.USER_FLAGS)
//                    .field(TYPE, KEYWORD)
//                    .field(NORMALIZER, CASE_INSENSITIVE)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.MEDIA_TYPE)
//                    .field(TYPE, KEYWORD)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.SUBTYPE)
//                    .field(TYPE, KEYWORD)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.FROM)
//                    .startObject(PROPERTIES)
//                    .startObject(JsonMessageConstants.EMailer.NAME)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, KEEP_MAIL_AND_URL)
//                    .endObject()
//                    .startObject(JsonMessageConstants.EMailer.DOMAIN)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, SIMPLE)
//                    .field(SEARCH_ANALYZER, KEYWORD)
//                    .endObject()
//                    .startObject(JsonMessageConstants.EMailer.ADDRESS)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, STANDARD)
//                    .field(SEARCH_ANALYZER, KEEP_MAIL_AND_URL)
//                    .startObject(FIELDS)
//                    .startObject(RAW)
//                    .field(TYPE, KEYWORD)
//                    .field(NORMALIZER, CASE_INSENSITIVE)
//                    .endObject()
//                    .endObject()
//                    .endObject()
//                    .endObject()
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.HEADERS)
//                    .field(TYPE, NESTED)
//                    .startObject(PROPERTIES)
//                    .startObject(JsonMessageConstants.HEADER.NAME)
//                    .field(TYPE, KEYWORD)
//                    .endObject()
//                    .startObject(JsonMessageConstants.HEADER.VALUE)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, KEEP_MAIL_AND_URL)
//                    .endObject()
//                    .endObject()
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.SUBJECT)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, KEEP_MAIL_AND_URL)
//                    .startObject(FIELDS)
//                    .startObject(RAW)
//                    .field(TYPE, KEYWORD)
//                    .field(NORMALIZER, CASE_INSENSITIVE)
//                    .endObject()
//                    .endObject()
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.TO)
//                    .startObject(PROPERTIES)
//                    .startObject(JsonMessageConstants.EMailer.NAME)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, KEEP_MAIL_AND_URL)
//                    .endObject()
//                    .startObject(JsonMessageConstants.EMailer.DOMAIN)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, SIMPLE)
//                    .field(SEARCH_ANALYZER, KEYWORD)
//                    .endObject()
//                    .startObject(JsonMessageConstants.EMailer.ADDRESS)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, STANDARD)
//                    .field(SEARCH_ANALYZER, KEEP_MAIL_AND_URL)
//                    .startObject(FIELDS)
//                    .startObject(RAW)
//                    .field(TYPE, KEYWORD)
//                    .field(NORMALIZER, CASE_INSENSITIVE)
//                    .endObject()
//                    .endObject()
//                    .endObject()
//                    .endObject()
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.CC)
//                    .startObject(PROPERTIES)
//                    .startObject(JsonMessageConstants.EMailer.NAME)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, KEEP_MAIL_AND_URL)
//                    .endObject()
//                    .startObject(JsonMessageConstants.EMailer.DOMAIN)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, SIMPLE)
//                    .field(SEARCH_ANALYZER, KEYWORD)
//                    .endObject()
//                    .startObject(JsonMessageConstants.EMailer.ADDRESS)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, STANDARD)
//                    .field(SEARCH_ANALYZER, KEEP_MAIL_AND_URL)
//                    .startObject(FIELDS)
//                    .startObject(RAW)
//                    .field(TYPE, KEYWORD)
//                    .field(NORMALIZER, CASE_INSENSITIVE)
//                    .endObject()
//                    .endObject()
//                    .endObject()
//                    .endObject()
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.BCC)
//                    .startObject(PROPERTIES)
//                    .startObject(JsonMessageConstants.EMailer.NAME)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, KEEP_MAIL_AND_URL)
//                    .endObject()
//                    .startObject(JsonMessageConstants.EMailer.DOMAIN)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, SIMPLE)
//                    .field(SEARCH_ANALYZER, KEYWORD)
//                    .endObject()
//                    .startObject(JsonMessageConstants.EMailer.ADDRESS)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, STANDARD)
//                    .field(SEARCH_ANALYZER, KEEP_MAIL_AND_URL)
//                    .startObject(FIELDS)
//                    .startObject(RAW)
//                    .field(TYPE, KEYWORD)
//                    .field(NORMALIZER, CASE_INSENSITIVE)
//                    .endObject()
//                    .endObject()
//                    .endObject()
//                    .endObject()
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.MAILBOX_ID)
//                    .field(TYPE, KEYWORD)
//                    .field(STORE, true)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.MIME_MESSAGE_ID)
//                    .field(TYPE, KEYWORD)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.TEXT_BODY)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, STANDARD)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.HTML_BODY)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, STANDARD)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.HAS_ATTACHMENT)
//                    .field(TYPE, BOOLEAN)
//                    .endObject()
//
//                    .startObject(JsonMessageConstants.ATTACHMENTS)
//                    .startObject(PROPERTIES)
//                    .startObject(JsonMessageConstants.Attachment.FILENAME)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, STANDARD)
//                    .endObject()
//                    .startObject(JsonMessageConstants.Attachment.TEXT_CONTENT)
//                    .field(TYPE, JsonMessageConstants.TEXT)
//                    .field(ANALYZER, STANDARD)
//                    .endObject()
//                    .startObject(JsonMessageConstants.Attachment.MEDIA_TYPE)
//                    .field(TYPE, KEYWORD)
//                    .endObject()
//                    .startObject(JsonMessageConstants.Attachment.SUBTYPE)
//                    .field(TYPE, KEYWORD)
//                    .endObject()
//                    .startObject(JsonMessageConstants.Attachment.FILE_EXTENSION)
//                    .field(TYPE, KEYWORD)
//                    .endObject()
//                    .startObject(JsonMessageConstants.Attachment.CONTENT_DISPOSITION)
//                    .field(TYPE, KEYWORD)
//                    .endObject()
//                    .endObject()
//                    .endObject()
//
//                    .endObject()
//                    .endObject();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}



// "name":{
//         "type": "text",
//         "analyzer" : "standard"
// },
// "address": {
//     "type" : "text",
//     "fields":{
//         "keyword":{
//         "type" : "keyword",
//         "ignore_above":  256
//         }
//     }
// }

//
//{
//        "query": {
//        "bool": {
//        "must": [
//        { "match": { "professor.facutly_type": "full-time" }},
//        { "match": { "professor.department": "finance" }}
//        ],
//        "must_not": [
//        { "match": { "course_description": "business" }}
//        ],
//        "filter": [
//        { "range":  { "students_enrolled": { "gte": 16 }}}
//        ]
//        }
//        }
//        }


//
//{
//        "query": {
//        "bool": {
//        "must": [
//        {
//        "match": {
//        "name": "accounting"
//        }
//        }
//        ],
//        "must_not": [
//        {
//        "match": {
//        "room": "e7"
//        }
//        }
//        ],
//        "should": [
//        {
//        "range": {
//        "students_enrolled": {
//        "gte": 10,
//        "lte": 20
//        }
//        }
//        }
//        ],
//        "minimum_should_match": 1
//        }
//        }
//        }