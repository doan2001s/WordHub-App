package com.workhub.global.core.network

object ApiContract {

    object Code {
        const val SUCCESS = "SUCCESS"
        const val EMPTY_RESPONSE = "EMPTY_RESPONSE"
        const val BAD_REQUEST = "BAD_REQUEST"
        const val UNAUTHORIZED = "UNAUTHORIZED"
        const val FORBIDDEN = "FORBIDDEN"
        const val NOT_FOUND = "NOT_FOUND"
        const val CONFLICT = "CONFLICT"
        const val VALIDATION_ERROR = "VALIDATION_ERROR"
        const val SERVER_ERROR = "SERVER_ERROR"
        const val NETWORK_ERROR = "NETWORK_ERROR"
        const val UNKNOWN_ERROR = "UNKNOWN_ERROR"
    }

    object Message {
        const val EMPTY_RESPONSE = "Phản hồi từ máy chủ không hợp lệ"
        const val BAD_REQUEST = "Request không hợp lệ"
        const val UNAUTHORIZED = "Phiên đăng nhập đã hết hạn"
        const val FORBIDDEN = "Bạn không có quyền truy cập"
        const val NOT_FOUND = "Không tìm thấy dữ liệu"
        const val CONFLICT = "Dữ liệu bị trùng hoặc xung đột"
        const val VALIDATION_ERROR = "Dữ liệu đầu vào không hợp lệ"
        const val SERVER_ERROR = "Hệ thống đang gặp sự cố, vui lòng thử lại sau"
        const val NETWORK_ERROR = "Không thể kết nối đến máy chủ"
        const val UNKNOWN_ERROR = "Đã có lỗi xảy ra, vui lòng thử lại"
    }

    object Header {
        const val AUTHORIZATION = "Authorization"
        const val REQUEST_ID = "X-Request-ID"
        const val DEVICE_ID = "X-Device-ID"
        const val APP_VERSION = "X-App-Version"
        const val PLATFORM = "X-Platform"
        const val ANDROID_PLATFORM = "android"
    }
}
