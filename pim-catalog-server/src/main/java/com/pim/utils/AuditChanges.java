package com.pim.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by pkulkar4 on 9/10/18.
 */

@Builder
@Getter @Setter
public class AuditChanges {

    private String propertyName;
    private Object OldValue;
    private Object newValue;

}
