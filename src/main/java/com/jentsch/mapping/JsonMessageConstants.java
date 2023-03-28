package com.jentsch.mapping;

public interface JsonMessageConstants {
    String MESSAGE_ID = "messageId";
    String UID = "uid";
    String MAILBOX_ID = "mailboxId";
    String THREAD_ID = "threadId";
    String IS_UNREAD = "isUnread";
    String IS_FLAGGED = "isFlagged";
    String IS_ANSWERED = "isAnswered";
    String IS_DRAFT = "isDraft";
    String HEADERS = "headers";
    String FROM = "from";
    String TO = "to";
    String CC = "cc";
    String BCC = "bcc";
    String SUBJECT = "subject";
    String DATE = "date";
    String SIZE = "size";
    String TEXT_BODY = "textBody";
    String HTML_BODY = "htmlBody";
    String SENT_DATE = "sentDate";
    String SAVE_DATE = "saveDate";
    String ATTACHMENTS = "attachments";
    String TEXT = "text";
    String MIME_MESSAGE_ID = "mimeMessageID";

    String MODSEQ = "modSeq";
    String USER_FLAGS = "userFlags";
    String IS_RECENT = "isRecent";
    String IS_DELETED = "isDeleted";
    String MEDIA_TYPE = "mediaType";
    String SUBTYPE = "subtype";
    String HAS_ATTACHMENT = "hasAttachment";

    interface EMailer {
        String NAME = "name";
        String ADDRESS = "address";
        String DOMAIN = "domain";
    }

    interface HEADER {
        String NAME = "name";
        String VALUE = "value";
    }

    interface Attachment {
        String TEXT_CONTENT = "textContent";
        String MEDIA_TYPE = "mediaType";
        String SUBTYPE = "subtype";
        String CONTENT_DISPOSITION = "contentDisposition";
        String FILENAME = "fileName";
        String FILE_EXTENSION = "fileExtension";
    }
}
