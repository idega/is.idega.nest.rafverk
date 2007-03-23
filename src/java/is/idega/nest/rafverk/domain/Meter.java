package is.idega.nest.rafverk.domain;


import com.idega.data.IDOEntity;

public interface Meter extends IDOEntity {

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#setPhase
	 */
	public void setPhase(String phase);

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#getPhase
	 */
	public String getPhase();

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#setNumber
	 */
	public void setNumber(String number);

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#getNumber
	 */
	public String getNumber();

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#setAmpere
	 */
	public void setAmpere(String ampere);

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#getAmpere
	 */
	public String getAmpere();

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#setRate
	 */
	public void setRate(String rate);

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#getRate
	 */
	public String getRate();

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#setDevice
	 */
	public void setDevice(String device);

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#getDevice
	 */
	public String getDevice();

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#setPlace
	 */
	public void setPlace(String place);

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#getPlace
	 */
	public String getPlace();

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#setContext
	 */
	public void setContext(String context);

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#getContext
	 */
	public String getContext();

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#setPriorityWithinContext
	 */
	public void setPriorityWithinContext(int priority);

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#getPriorityWithinContext
	 */
	public int getPriorityWithinContext();

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#setElectricalInstallation
	 */
	public void setElectricalInstallation(ElectricalInstallation electricalInstallation);

	/**
	 * @see is.idega.nest.rafverk.domain.MeterBMPBean#getElectricalInstallation
	 */
	public ElectricalInstallation getElectricalInstallation();
}