/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.asta.app2.tutorial.helloorchestra;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * JSF backing-bean (aka controller class) for reservation.jsp.
 */
public class ReservationView
{
    private final Log log = LogFactory.getLog(ReservationView.class);

    private ReservationBook book;
    private Reservation reservation;
    private boolean viewMode = false;
    private String reservationState;

    public int getInstanceId()
    {
        return System.identityHashCode(this);
    }

    public void setBook(ReservationBook book)
    {
        this.book = book;
    }

    public Reservation getReservation()
    {
        if (reservation == null)
        {
            reservation = new Reservation();
        }
        return reservation;
    }

    public void setReservation(Reservation reservation)
    {
        this.reservation = reservation;
        this.viewMode = true;
    }

    public boolean getViewMode()
    {
        return viewMode;
    }

    public String getReservationState()
    {
        return reservationState;
    }

    private boolean isAvailable(Date start, int days)
    {
        // the logic here would normally be on some "business service" class rather
        // than buried here inside the presentation class...

        // The hotel is closed on sat/sun nights.
        // Calendar.SUNDAY=1, SATURDAY=6
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        int dow = cal.get(Calendar.DAY_OF_WEEK);
        if (dow == Calendar.SUNDAY)
        {
            return false;
        }
        if (dow + days > Calendar.SATURDAY)
        {
            return false;
        }

        // otherwise, ok
        return true;
    }

    public String checkAvailability()
    {
        if (isAvailable(reservation.getStart(), reservation.getDays()))
        {
            reservationState = "Available";
        }
        else
        {
            reservationState = "Not available";
        }
        return null;
    }

    public String save()
    {
        if (isAvailable(reservation.getStart(), reservation.getDays()))
        {
            book.getReservations().add(reservation);
            return "save";
        }
        reservationState = "Not Available";
        return null;
    }

    public String remove()
    {
        book.getReservations().remove(reservation);
        return "delete";
    }
}
