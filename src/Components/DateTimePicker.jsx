import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

let today =
  new Date().toISOString().slice(0, 10) +
  " / " +
  new Date().toLocaleTimeString();
var end = new Date();
end.setHours(23, 59, 59);

function DateTimePicker() {
  const [selectedDate, setSelectedDate] = useState(null);

  const handleDateChange = (date) => {
    setSelectedDate(date);
  };

  return (
    <>
      <DatePicker
        selected={selectedDate}
        onChange={handleDateChange}
        showTimeSelect
        timeFormat="HH:mm"
        timeIntervals={15}
        dateFormat="MMMM d, yyyy h:mm aa"
        placeholderText={today}
      />
    </>
  );
}

export default DateTimePicker;
