/*
 *  Copyright (c) 2001 Sun Microsystems, Inc.  All rights
 *  reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *
 *  1. Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *  3. The end-user documentation included with the redistribution,
 *  if any, must include the following acknowledgment:
 *  "This product includes software developed by the
 *  Sun Microsystems, Inc. for Project JXTA."
 *  Alternately, this acknowledgment may appear in the software itself,
 *  if and wherever such third-party acknowledgments normally appear.
 *
 *  4. The names "Sun", "Sun Microsystems, Inc.", "JXTA" and "Project JXTA" must
 *  not be used to endorse or promote products derived from this
 *  software without prior written permission. For written
 *  permission, please contact Project JXTA at http://www.jxta.org.
 *
 *  5. Products derived from this software may not be called "JXTA",
 *  nor may "JXTA" appear in their name, without prior written
 *  permission of Sun.
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL SUN MICROSYSTEMS OR
 *  ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 *  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 *  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 *  USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 *  OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 *  SUCH DAMAGE.
 *  ====================================================================
 *
 *  This software consists of voluntary contributions made by many
 *  individuals on behalf of Project JXTA.  For more
 *  information on Project JXTA, please see
 *  <http://www.jxta.org/>.
 *
 *  This license is based on the BSD license adopted by the Apache Foundation.
 *
 *  $Id: exit.java,v 1.21 2007/02/09 23:12:54 hamada Exp $
 */
package net.jxta.impl.shell.bin.exit;

import java.util.Enumeration;
import net.jxta.id.ID;
import net.jxta.impl.shell.ShellApp;
import net.jxta.impl.shell.ShellEnv;
import net.jxta.impl.shell.ShellObject;
import net.jxta.impl.shell.bin.Shell.Shell;
import net.jxta.rendezvous.RendezVousService;

/**
 * Exit the Shell
 */
public class exit extends ShellApp {

    /**
     * {@inheritDoc}
     */
    public int startApp(String[] args) {
        ShellEnv env = getEnv();
        ShellObject obj = env.get("shell");

        if (obj == null) {
            consoleMessage("Cannot exit (no shell object)");
            return ShellApp.appMiscError;
        }

        Shell sh = (Shell) obj.getObject();

        if (sh == null) {
            consoleMessage("Cannot exit (null shell)");
            return ShellApp.appMiscError;
        }

        // Don't accept to exit if the Shell is embedded in another application
        // and if Shell is Root Shell
        if (Shell.isEmbedded() && sh.isRootShell()) {
            consoleMessage("Won't exit (Embedded shell)");
            return ShellApp.appNoError;
        }

        //U2U Implementation
        //disconnecting from the rendezvous, only for the edge peers
        RendezVousService rendezVousService = getGroup().getRendezVousService();

        if(!rendezVousService.isRendezVous())
        {
            Enumeration<ID> rdvPeers = rendezVousService.getConnectedRendezVous();

            consoleMessage("Sending disconnectFromRendezVous to all the rendezvous");
            while(rdvPeers.hasMoreElements())
            {
                //disconnecting from the rendezvous
                rendezVousService.disconnectFromRendezVous(rdvPeers.nextElement());
            }
        }
        //EOU2U Implementation

        sh.stopApp();
        return ShellApp.appNoError;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Exit the Shell";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void help() {
        println("NAME");
        println("     exit - " + getDescription());
        println(" ");
        println("SYNOPSIS");
        println(" ");
        println("     exit");
        println(" ");
        println("DESCRIPTION");
        println(" ");
        println("'exit' command is used to exit the Shell");
        println(" ");
        println("SEE ALSO");
        println("    Shell");
    }
}
