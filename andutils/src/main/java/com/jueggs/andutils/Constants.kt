package com.jueggs.andutils

// divers
const val STATE_VIEWMODEL = "STATE_VIEWMODEL"
const val SCROLL_DIRECTION_TOP = -1
const val DEFAULT_DATE_FORMAT = "MM/dd/yy"
const val DEFAULT_DATETIME_FORMAT = "$DEFAULT_DATE_FORMAT - hh:mm a"

// directory
const val FONT_DIR = "fonts"

// file type
const val CONTENT_IMAGE_TYPE = "image/*"

// named annotation
const val NAMED_APP_CONTEXT = "NAMED_APP_CONTEXT"
const val NAMED_ACTIVITY_CONTEXT = "NAMED_ACTIVITY_CONTEXT"

// tags
const val TAG_UNHANDLED_EXCEPTION = "UNHANDLED_EXCEPTION"
const val TAG_EXCEPTION = "EXCEPTION"
const val TAG_DATABASE_FAILURE = "DATABASE_FAILURE"
const val TAG_STORAGE_FAILURE = "STORAGE_FAILURE"
const val TAG_ANY_TAG = "ANY_TAG"
const val TAG_DEBUG = "MYDEBUG"
const val TAG_ERROR = "ERROR"
const val TAG_NETWORK = "NETWORK"
const val TAG_FONT_CACHE = "FONTCACHE"

// log
const val LOG_LEVEL_DEBUG = 0
const val LOG_LEVEL_ERROR = 1

// error messages
const val ERROR_NO_EVENTHANDLER_ID = "No variable id for eventhandler set. Override %s to set a binding variable id or remove eventhandler"
const val ERROR_NO_INCLUDED_LAYOUT = "No included layout found although announced. Set %s to false or include layout with id set to '@+id/%s'"
const val ERROR_NO_SETVARIABLE_METHOD = "Method '%s' not found, check generated binding class(es)"