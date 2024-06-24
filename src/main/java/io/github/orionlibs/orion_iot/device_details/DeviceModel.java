package io.github.orionlibs.orion_iot.device_details;

import io.github.orionlibs.core.abstraction.OrionModel;
import io.github.orionlibs.core.calendar.SQLTimestamp;
import io.github.orionlibs.core.data.source.database.IgnoreForORM;
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
public class DeviceModel implements OrionModel
{
    @IgnoreForORM
    private Long deviceID;
    private String deviceName;
    private String connectionURL;


    public static DeviceModel of()
    {
        return DeviceModel.builder().build();
    }


    public static DeviceModel of(Long deviceID)
    {
        return DeviceModel.builder().deviceID(deviceID).build();
    }


    @Override
    public boolean equals(Object other)
    {
        if(this == other)
        {
            return true;
        }
        else if(other instanceof DeviceModel)
        {
            DeviceModel otherTemp = (DeviceModel)other;
            return Objects.equals(deviceID, otherTemp.getDeviceID());
        }
        else
        {
            return false;
        }
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(deviceID);
    }


    @Override
    public DeviceModel clone()
    {
        return (DeviceModel)CloningService.clone(this);
    }


    @Override
    public DeviceModel getCopy()
    {
        return this.clone();
    }
}