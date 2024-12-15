package com.depart.depart6.Enum;

import lombok.Data;


public enum FriendshipStatus {
    PENDING,    // İstek gönderildi, henüz onay bekliyor
    ACCEPTED,   // İstek kabul edildi
    DECLINED,   // İstek reddedildi
    BLOCKED     // Kullanıcı engellendi
}
