package io.github.orionlibs.orion_iot.device_message;

import io.github.orionlibs.core.abstraction.OrionModel;
import io.github.orionlibs.core.calendar.SQLTimestamp;
import io.github.orionlibs.core.object.CloningService;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DevicePayloadModel implements OrionModel
{
    //@IgnoreForORM
    private Long devicePayloadID;
    private String topic;
    private String payload;
    private SQLTimestamp timestampOfRecord;


    public static DevicePayloadModel of()
    {
        return DevicePayloadModel.builder().build();
    }


    public static DevicePayloadModel of(Long devicePayloadID)
    {
        return DevicePayloadModel.builder().devicePayloadID(devicePayloadID).build();
    }


    @Override
    public boolean equals(Object other)
    {
        if(this == other)
        {
            return true;
        }
        else if(other instanceof DevicePayloadModel)
        {
            DevicePayloadModel otherTemp = (DevicePayloadModel)other;
            return Objects.equals(devicePayloadID, otherTemp.getDevicePayloadID());
        }
        else
        {
            return false;
        }
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(devicePayloadID);
    }


    @Override
    public DevicePayloadModel clone()
    {
        return (DevicePayloadModel)CloningService.clone(this);
    }


    @Override
    public DevicePayloadModel getCopy()
    {
        return this.clone();
    }
}